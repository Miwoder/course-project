package com.leverx.govoronok.service;


import com.leverx.govoronok.model.Game;
import com.leverx.govoronok.model.GameGenre;
import com.leverx.govoronok.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("gameService")
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }


    public List<Game> getGame(){
        List<Game> games = gameRepository.findByName("Outlast");
        games.forEach(System.out::println);
        return games;
    }

    public void test() {
        // Save a new customer
        Game newGame = new Game();
        newGame.setGenre(GameGenre.HORROR);
        newGame.setName("Outlast3");

        gameRepository.save(newGame);

        // Find a customer by ID
        Optional<Game> result = gameRepository.findById(1L);
        result.ifPresent(System.out::println);

        // Find customers by last name
        List<Game> games = gameRepository.findByName("Outlast2");
        games.forEach(System.out::println);

        // List all customers
        Iterable<Game> iterator = gameRepository.findAll();
        iterator.forEach(System.out::println);

        // Count number of customer
        long count = gameRepository.count();
        System.out.println("Number of games: " + count);
    }
}
