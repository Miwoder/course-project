package com.leverx.govoronok.controller;


import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.model.GameObject;
import com.leverx.govoronok.service.GameObjectService;
import com.leverx.govoronok.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GameObjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameObject.class);

    private GameObjectService gameObjectService;

    @Autowired
    public GameObjectController(GameObjectService gameObjectService){
        this.gameObjectService = gameObjectService;
    }

    @GetMapping("/users/{id}/objects")
    public String getAllUserComments(Model model, @PathVariable("id") Long id) {
        List<GameObject> gameObjects = gameObjectService.getGameObjectsForUserById(id);
        model.addAttribute("gameObjects", gameObjects);
        return "gameObject/gameObjects";
    }


}
