package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.model.User;
import com.leverx.govoronok.service.CommentService;
import com.leverx.govoronok.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;
    private UserService userService;

    @Autowired
    public CommentController(CommentService commentService,  UserService userService){
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/{id}/comments/{commentId}")
    public String getCommentForUser(Model model, @PathVariable("id") Long traderId,
                                    @PathVariable("commentId") Long commentId) {
        Comment comment = commentService.getCommentForUserByCommentId(commentId, traderId);
        model.addAttribute("comment", comment);
        return "comment/comment";
    }

    @GetMapping("/{id}/comments")
    public String getAllUserComments(Model model, @PathVariable("id") Long id) {
        List<Comment> comments = commentService.getCommentsForUserById(id);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());
        return "comment/allComments";
    }

    @PostMapping("/{id}/comments")
    public String addNewComment(Principal principal, @PathVariable("id") Long traderId, @ModelAttribute("newComment") Comment newComment) {
        newComment.setTrader(userService.getUserById(traderId).get());
        User user = userService.findByUsername(principal.getName());
        newComment.setAuthor(user);
        commentService.addNewComment(newComment);
        return "redirect:/users/comment/confirmComment";
    }

    @GetMapping("/{id}/comments/{commentId}/edit")
    public String editCommentForUser(Model model, @PathVariable("id") Long traderId,
                                    @PathVariable("commentId") Long commentId, Principal principal) {
        if(!commentService.getCommentById(commentId).get().getAuthor().getId().equals(userService.findByUsername(principal.getName()).getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author");
        }
            Comment comment = commentService.getCommentForUserByCommentId(commentId, traderId);
        model.addAttribute("comment", comment);
        return "/comment/commentEdit";
    }

    @PutMapping("/{id}/comments/{commentId}")
    public String updateComment(@PathVariable("id") Long traderId, Principal principal, @ModelAttribute("newComment") Comment updatedComment,
                                @PathVariable("commentId") Long commentId) {
        if(commentService.getCommentById(commentId).get().getAuthor().getId().equals(userService.findByUsername(principal.getName()).getId())) {
            commentService.updateComment(commentId, updatedComment);
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author");
        }
        return "redirect:/users/comment/confirmComment";
    }

    @GetMapping("/comment/confirmComment")
    public String getConfirmAlert() {
        return "/comment/confirmComment";
    }

    @DeleteMapping("/{traderId}/comments/{commentId}")
    public String deleteCommentForTrader(Principal principal, @PathVariable("commentId") Long commentId){
        if(commentService.getCommentById(commentId).get().getAuthor().getId().equals(userService.findByUsername(principal.getName()).getId())) {
            commentService.deleteCommentById(commentId);
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author");
        }
        return "redirect:/traders";
    }

}
