package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Role;
import com.leverx.govoronok.service.CommentService;
import com.leverx.govoronok.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administration")
public class AdministratorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorController.class);

    private CommentService commentService;
    private UserService userService;

    @Autowired
    public AdministratorController(CommentService commentService,  UserService userService){
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/comments")
    public String getAllUnconfirmedComments(Model model) {
        model.addAttribute("comments", commentService.getAllUnconfirmedComments());
        return "comment/unconfirmedComments";
    }

    @GetMapping("/users")
    public String getAllUnconfirmedUsers(Model model) {
        model.addAttribute("users", userService.getAllUnconfirmedUsers(Role.TRADER));
        return "authentication/unconfirmedUsers";
    }
}
