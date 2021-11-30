package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.service.CommentService;
import com.leverx.govoronok.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;
    private UserService userService;

    @Autowired
    public CommentController(CommentService commentService,  UserService userService){
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/users/{id}/comments/{commentId}")
    public String getCommentForUser(Model model, @PathVariable("id") Long traderId,
                                    @PathVariable("commentId") Long commentId) {
        Comment comment = commentService.getCommentForUserByCommentId(commentId, traderId);
        model.addAttribute("comment", comment);
        return "comment/comment";
    }

    @GetMapping("/users/{id}/comments")
    public String getAllUserComments(Model model, @PathVariable("id") Long id) {
        List<Comment> comments = commentService.getCommentsForUserById(id);
        model.addAttribute("traderId", id);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());
        return "comment/allComments";
    }

    //TODO: ADD AUTHOR
    @PostMapping("/users/{id}/comments")
    public String addNewComment(Model model, @PathVariable("id") Long traderId, @ModelAttribute("newComment") Comment newComment) {
        newComment.setTrader(userService.getUserById(traderId).get());
        newComment.setAuthor(userService.getUserById((long) 27).get());
        commentService.addNewComment(newComment);
        return "redirect:/comment/confirmComment";
    }

    @GetMapping("/users/{id}/comments/{commentId}/edit")
    public String editCommentForUser(Model model, @PathVariable("id") Long traderId,
                                    @PathVariable("commentId") Long commentId) {
        Comment comment = commentService.getCommentForUserByCommentId(commentId, traderId);
        model.addAttribute("comment", comment);
        return "redirect:/comment/commentEdit";
    }

    //TODO: Add author
    @PutMapping("/users/{id}/comments/{commentId}")
    public String updateComment(Model model, @PathVariable("id") Long traderId, @ModelAttribute("newComment") Comment updatedComment,
                                @PathVariable("commentId") Long commentId) {
        commentService.updateComment(commentId, updatedComment);
        return "redirect:/comment/confirmComment";
    }

    @GetMapping("/comment/confirmComment")
    public String getConfirmAlert() {
        return "/comment/confirmComment";
    }

    //TODO:delete by author
    @DeleteMapping("/users/{traderId}/comments/{commentId}")
    public String deleteCommentForTraderByAuthor(Model model, @PathVariable("traderId") Long traderId,
                                                 @PathVariable("commentId") Long commentId){
        commentService.deleteCommentById(commentId);
        return null;
    }

}
