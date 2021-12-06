package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.*;
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
import java.util.Optional;
import java.util.UUID;


@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToSingIn(){
        return "redirect:/traders";
    }

    @GetMapping("/signup")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/authentication/signup";
    }

    @PostMapping("/signup")
    public String addNewUser(@ModelAttribute("user") User user) {
        if(user.getRole()==Role.USER){
            user.setConfirmedByAdmin(Boolean.TRUE);
        }
        userService.addNewUser(user);
        return "redirect:/authentication/confirmAlert";
    }

    @GetMapping("/signin")
    public String signin(){
        return "/authentication/signin";
    }

    @GetMapping("/authentication/confirm/{code}")
    public String confirmUserCode(@PathVariable("code") UUID code) {
        userService.confirmUser(code);
        return "redirect:/signin";
    }

    @GetMapping("/authentication/confirmAlert")
    public String getConfirmAlert() {
        return "/authentication/confirmAlert";
    }

    @GetMapping("/traders")
    public String getAllTraders(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("traders", userService.getAllTraders(Role.TRADER));
        return "user/traders";
    }

    @PostMapping("/traders")
    public String addNewTraderByUser(Model model, @ModelAttribute("newUser") User newUser) {
        newUser.setRole(Role.TRADER);
        newUser.setEmail("adminmail@mail.com");
        newUser.setPassword("jayehfub");
        userService.addNewUser(newUser);
        return "redirect:/traders";
    }

}