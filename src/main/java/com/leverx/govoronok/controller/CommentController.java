package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String getCommentForUser(Model model, @PathVariable("id") Long traderId, @PathVariable("commentId") Long commentId) {
        Comment comment = commentService.getCommentForUserByCommentId(commentId, traderId);
        model.addAttribute("comment", comment);
        return "comment/comment";
    }

    @GetMapping("/users/{id}/comments")
    public String getAllUserComments(Model model, @PathVariable("id") Long id) {
        List<Comment> comments = commentService.getCommentsForUserById(id);
        model.addAttribute("comments", comments);
        return "comment/allComments";
    }

    @GetMapping("/comments")
    public String getAllComments(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "comment/allComments";
    }
}
