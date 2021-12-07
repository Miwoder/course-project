package com.leverx.govoronok.service;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.model.GameObject;
import com.leverx.govoronok.repository.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Optional<GameObject> getGameObjectById(Long id){
        return gameObjectRepository.findById(id);
    }

    public void addNewGameObject(GameObject gameObject){
        gameObjectRepository.save(gameObject);
    }

    public void updateGameObjectById(Long id, GameObject updatedObject){
        Optional<GameObject> objectToBeUpdated = gameObjectRepository.findById(id);
        if (objectToBeUpdated.isPresent()) {
            objectToBeUpdated.get().setTitle(updatedObject.getTitle());
            objectToBeUpdated.get().setUpdatedAt(LocalDate.now());
            gameObjectRepository.save(objectToBeUpdated.get());
        } else {
            System.out.println("ERROR UPD");
        }
    }

    public void deleteGameObjectById(Long id){
        gameObjectRepository.deleteById(id);
    }

}
