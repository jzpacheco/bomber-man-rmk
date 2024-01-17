package com.jzpacheco.bombermanrmk.controllers;

import com.jzpacheco.bombermanrmk.model.Game;
import com.jzpacheco.bombermanrmk.model.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @MessageMapping("/joinGame")
    @SendTo("/topic/game")
    public Player handlePlayerJoinGame(Player player){
        return player;
    };


    @MessageMapping("/createGame")
    @SendTo("/topic/game")
    private Game initializer(Player player){
        Game game = new Game(player);
        return game;
    }
}
