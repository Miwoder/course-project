package com.leverx.govoronok.service;

import com.leverx.govoronok.repository.CommentRepository;
import com.leverx.govoronok.repository.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gameObjectService")
public class GameObjectService {
    private GameObjectRepository gameObjectRepository;

    @Autowired
    public GameObjectService(GameObjectRepository gameObjectRepository){
        this.gameObjectRepository = gameObjectRepository;
    }
}
