package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello";
    }
//
//    private final GameDao gameDao;
//
//    @Autowired
//    public UsersController(GameDao gameDao) {
//        this.gameDao = gameDao;
//    }
//
//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("games", gameDao.index());
//        return "game/index";
//    }
//
//    @GetMapping(path="/{id}")
//    public String show(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("games", gameDao.showGame(id));
//        return "game/show";
//    }
//
//
//    @GetMapping("/new")
//    public String newGame(@ModelAttribute("game") Game game){
//        return "game/newGame";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("game") Game game){
//        gameDao.save(game);
//        return "redirect:/game";
//    }

//
//    @PutMapping(path="/games/{id}")
//    public String updateGame(@PathVariable("id") Long id, Model model){
//        return null;
//    }


}