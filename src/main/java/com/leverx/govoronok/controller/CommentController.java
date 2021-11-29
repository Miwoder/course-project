package com.leverx.govoronok.controller;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.model.Game;
import com.leverx.govoronok.model.GameGenre;
import com.leverx.govoronok.service.CommentService;
import com.leverx.govoronok.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

//    @GetMapping("/users/:id/comments ")
//    public String getAllUserComments(Model model, @PathVariable("id") Long id) {
//        Optional<Comment> comment = commentService.getCommentById(id);
//        if (game.isPresent()) {
//            model.addAttribute("game", game.get());
//            List<GameGenre> gameGenreList = Arrays.asList(GameGenre.values());
//            model.addAttribute("gameGenres", gameGenreList);
//            return "game/gameEdit";
//        } else {
//            return "game/games";
//        }
//    }
}
