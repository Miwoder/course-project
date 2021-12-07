package com.leverx.govoronok.controller;


import com.leverx.govoronok.model.*;
import com.leverx.govoronok.service.GameObjectService;
import com.leverx.govoronok.service.GameService;
import com.leverx.govoronok.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
public class GameObjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameObject.class);

    private GameObjectService gameObjectService;
    private UserService userService;
    private GameService gameService;

    @Autowired
    public GameObjectController(GameObjectService gameObjectService, UserService userService, GameService gameService){
        this.gameObjectService = gameObjectService;
        this.userService = userService;
        this.gameService = gameService;
    }

    @GetMapping("/users/{id}/objects")
    public String getAllUserComments(Model model, @PathVariable("id") Long id) {
        List<GameObject> gameObjects = gameObjectService.getGameObjectsForUserById(id);
        model.addAttribute("gameObjects", gameObjects);
        return "gameObject/gameObjects";
    }

    @Transactional
    @GetMapping("/my")
    public String getMyObjects(Model model, Principal principal) {
        if(userService.findByUsername(principal.getName()).getRole().equals(Role.USER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not a trader");
        }
        else{
            List<GameObject> gameObjects = gameObjectService.getGameObjectsForUserById(userService.findByUsername(principal.getName()).getId());

            model.addAttribute("gameObjects", gameObjects);
            model.addAttribute("traderId",  userService.findByUsername(principal.getName()).getId());
            model.addAttribute("newGameObject", new GameObject());
//            String game = null;
//            model.addAttribute("game", game);
            return "gameObject/myPage";
        }
    }

    @PostMapping("/my")
    public String addNewComment(Principal principal, @ModelAttribute("newObject") GameObject newGameObject) {
        newGameObject.setAuthor(userService.findByUsername(principal.getName()));
        newGameObject.setGame(gameService.getGameByName(newGameObject.getGame().getName()));
        gameObjectService.addNewGameObject(newGameObject);
        return "redirect:/my";
    }



    @GetMapping("/users/{traderId}/objects/{objectId}/edit")
    public String editGameObject(Model model, @PathVariable("traderId") Long traderId,
                                     @PathVariable("objectId") Long objectId, Principal principal) {
        if(!gameObjectService.getGameObjectById(objectId).get().getAuthor().getId().equals(userService.findByUsername(principal.getName()).getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author");
        }
        model.addAttribute("gameObject", gameObjectService.getGameObjectById(objectId));
        return "/gameObject/gameObjectEdit";
    }

    @PatchMapping("/users/{traderId}/objects/{objectId}")
    public String updateGameObject(@PathVariable("traderId") Long traderId, Principal principal,
                                @ModelAttribute("newObject") GameObject updatedObject, @PathVariable("objectId") Long objectId) {
        if(gameObjectService.getGameObjectById(objectId).get().getAuthor().getId().equals(userService.findByUsername(principal.getName()).getId())) {
            gameObjectService.updateGameObjectById(objectId, updatedObject);
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author");
        }
        return "redirect:/my";
    }






    @DeleteMapping("/users/{traderId}/objects/{objectId}")
    public String deleteGameObjectForTrader(@PathVariable("traderId") Long traderId,
                                                 Principal principal, @PathVariable("objectId") Long objectId){
        if(gameObjectService.getGameObjectById(objectId).get().getAuthor().getId().equals(userService.findByUsername(principal.getName()).getId())) {
            gameObjectService.deleteGameObjectById(objectId);
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author");
        }
        return "redirect:/my";
    }


}
