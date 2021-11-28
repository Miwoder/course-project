package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Game;
import com.leverx.govoronok.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GameController.class);

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

//    @Autowired(required = true)
//    public void setGameService(GameService gameService){
//        this.gameService = gameService;
//    }

    @GetMapping(value = "gameShow")
    public String listGames(Model model){
        gameService.getGame();
        model.addAttribute("game", new Game());
        model.addAttribute("listGames", this.gameService.getGame());
        return "gameShow";
    }

    @GetMapping("/say")
    public String sayHello() {
        gameService.test();
        return "test";
    }
}
