package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Game;
import com.leverx.govoronok.model.GameGenre;
import com.leverx.govoronok.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
@Slf4j
public class GameController {
    private final GameService gameService;

    @GetMapping()
    public String getAllGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "game/games";
    }

    @GetMapping("/new")
    public String createNewGame(Model model) {
        List<GameGenre> gameGenreList = Arrays.asList(GameGenre.values());
        model.addAttribute("gameGenres", gameGenreList);
        model.addAttribute("game", new Game());
        return "game/newGame";
    }

    @PostMapping()
    public String addNewGame(@ModelAttribute("game") Game game) {
        gameService.addNewGame(game);
        return "redirect:/games";
    }

    @GetMapping("/{id}/edit")
    public String editGame(Model model, @PathVariable("id") Long id) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            model.addAttribute("game", game.get());
            List<GameGenre> gameGenreList = Arrays.asList(GameGenre.values());
            model.addAttribute("gameGenres", gameGenreList);
            return "game/gameEdit";
        } else {
            return "game/games";
        }
    }

    @PutMapping("/{id}")
    public String updateGame(@ModelAttribute("game") Game game, @PathVariable("id") Long id) {
        gameService.updateGame(id, game);
        return "redirect:/games";
    }

}
