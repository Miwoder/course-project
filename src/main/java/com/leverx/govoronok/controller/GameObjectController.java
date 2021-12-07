package com.leverx.govoronok.controller;


import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.model.GameObject;
import com.leverx.govoronok.model.Role;
import com.leverx.govoronok.service.GameObjectService;
import com.leverx.govoronok.service.GameService;
import com.leverx.govoronok.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
public class GameObjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameObject.class);

    private GameObjectService gameObjectService;
    private UserService userService;

    @Autowired
    public GameObjectController(GameObjectService gameObjectService, UserService userService){
        this.gameObjectService = gameObjectService;
        this.userService = userService;
    }

    @GetMapping("/users/{id}/objects")
    public String getAllUserComments(Model model, @PathVariable("id") Long id) {
        List<GameObject> gameObjects = gameObjectService.getGameObjectsForUserById(id);
        model.addAttribute("gameObjects", gameObjects);
        return "gameObject/gameObjects";
    }

    @GetMapping("/my")
    public String getMyObjects(Model model, Principal principal) {
        if(userService.findByUsername(principal.getName()).getRole().equals(Role.USER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not a trader");
        }
        else{
            List<GameObject> gameObjects = gameObjectService.getGameObjectsForUserById(userService.findByUsername(principal.getName()).getId());
            model.addAttribute("gameObjects", gameObjects);
            return "gameObject/gameObjects";
        }
    }


}
