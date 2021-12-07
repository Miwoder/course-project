package com.leverx.govoronok.service;

import com.leverx.govoronok.model.GameObject;
import com.leverx.govoronok.repository.CommentRepository;
import com.leverx.govoronok.repository.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gameObjectService")
public class GameObjectService {
    private GameObjectRepository gameObjectRepository;

    @Autowired
    public GameObjectService(GameObjectRepository gameObjectRepository){
        this.gameObjectRepository = gameObjectRepository;
    }

    public List<GameObject> getGameObjectsForUserById(Long id){
        return gameObjectRepository.getGameObjectsByAuthor_Id(id);
    }

}
