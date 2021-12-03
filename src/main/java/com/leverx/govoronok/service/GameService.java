package com.leverx.govoronok.service;


import com.leverx.govoronok.model.Game;
import com.leverx.govoronok.model.GameGenre;
import com.leverx.govoronok.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("gameService")
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }


    public List<Game> getAllGames(){
        List<Game> games = gameRepository.findAll();
        return games;
    }

    public Optional<Game> getGameById(Long id){
        return gameRepository.findById(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    public void updateGame(Long id, Game updatedGame){
        Optional<Game> gameToBeUpdated = gameRepository.findById(id);
        if (gameToBeUpdated.isPresent()) {
            gameToBeUpdated.get().setName(updatedGame.getName());
            gameToBeUpdated.get().setGenre(updatedGame.getGenre());
            gameRepository.save(gameToBeUpdated.get());
        } else {
            System.out.println("ERROR UPD");
        }

    }

    public void addNewGame(Game game){
        gameRepository.save(game);
    }
}
