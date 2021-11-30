package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.model.User;
import com.leverx.govoronok.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
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

    //TODO: ADD AUTHOR AND TRADER
    @PostMapping("/users/{id}/comments")
    public String addNewComment(Model model, @PathVariable("id") Long traderId, @ModelAttribute("newComment") Comment newComment) {
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
        commentService.deleteComment(commentId);
        return null;
    }

    @GetMapping("/comments")
    public String getAllComments(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "comment/allComments";
    }
}
