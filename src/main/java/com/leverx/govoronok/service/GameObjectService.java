package com.leverx.govoronok.service;

import com.leverx.govoronok.model.GameObject;
import com.leverx.govoronok.repository.GameObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameObjectService {
    private final GameObjectRepository gameObjectRepository;

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

    @Transactional
    public void deleteGameObjectById(Long id){
        gameObjectRepository.deleteById(id);
    }

}
