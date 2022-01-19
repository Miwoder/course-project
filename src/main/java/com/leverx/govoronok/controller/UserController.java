package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.*;
import com.leverx.govoronok.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;


@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

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
    public String addNewUser(@Valid @ModelAttribute("user") User user, Errors errors, BindingResult bindingResult) {
        if (userService.findByUsername(user.getEmail()) != null) {
            errors.rejectValue("email", "email.notUnique", "Email must be unique.");
        }
        if (user.getEmail()  == null || user.getEmail().length() == 0) {
            bindingResult.rejectValue("email", "email.missing", "Must enter email");
        }
        if(errors.hasErrors() || bindingResult.hasErrors()){
            return "redirect:/signup";
        }
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

    @GetMapping("/authentication/forgot_password")
    public String resetPassword(){
        return "/authentication/resetPassword";
    }

    @PostMapping("/authentication/forgot_password")
    public String resetPassword(@Valid @ModelAttribute("username") String username, Errors errors, BindingResult bindingResult) {
        if(errors.hasErrors() || bindingResult.hasErrors()){
            return "redirect:/signin";
        }
        userService.resetPasswordForUserByEmail(username);
        return "redirect:/authentication/confirmAlert";
    }

    @GetMapping("/authentication/reset")
    public String setNewPassword(){
        return "authentication/newPassword";
    }

    @PostMapping("/authentication/reset")
    public String setNewPassword(@ModelAttribute("code") UUID code, @Valid String password,
                                 @Valid String passwordConfirm, Errors errors) {
        if (!password.equals(passwordConfirm)) {
            errors.rejectValue("confirmPassword", "confirmPassword.dontMatch", "Passwords dont match.");
        }
        if(errors.hasErrors()){
            return "redirect:/signin";
        }
        if(password.equals(passwordConfirm)){
            userService.setNewPasswordWithCode(code , password);
        }
        return "redirect:/signin";
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
    public String addNewTraderByUser(@Valid @ModelAttribute("newUser") User newUser, Errors errors) {
        if(errors.hasErrors()){
            return "redirect:/traders";
        }
        newUser.setRole(Role.TRADER);
        newUser.setEmail("adminmail@mail.com");
        newUser.setPassword("jayehfub");
        newUser.setApproved(Boolean.TRUE);
        userService.addNewUser(newUser);
        return "redirect:/traders";
    }

}