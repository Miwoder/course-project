package com.leverx.govoronok.service;


import com.leverx.govoronok.model.Game;
import com.leverx.govoronok.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public List<Game> getAllGames(){
        List<Game> games = gameRepository.findAll();
        return games;
    }

    public Optional<Game> getGameById(Long id){
        return gameRepository.findById(id);
    }

    public Game getGameByName(String name){
        return gameRepository.getGameByName(name);
    }

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
