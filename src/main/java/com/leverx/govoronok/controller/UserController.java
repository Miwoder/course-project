package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Game;
import com.leverx.govoronok.model.Role;
import com.leverx.govoronok.model.User;
import com.leverx.govoronok.service.GameService;
import com.leverx.govoronok.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToSingIn(){
        return "authentication/signin";
    }

    @GetMapping("/signup")
    public String createNewUser(Model model) {
        List<Role> roleList = Arrays.asList(Role.values());
        roleList.remove(0);
        model.addAttribute("roleList", roleList);
        model.addAttribute("user", new User());
        return "authentication/signup";
    }

    @PostMapping()
    public String addNewGame(@ModelAttribute("user") User user) {
        userService.addNewUser(user);
        return "redirect:authentication/confirmAlert";
    }

}