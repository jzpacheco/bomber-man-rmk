package com.jzpacheco.bombermanrmk.controllers;

import com.jzpacheco.bombermanrmk.model.Game;
import com.jzpacheco.bombermanrmk.model.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private Game game;

    @MessageMapping("/joinGame")
    @SendTo("/topic/game")
    public Game handlePlayerJoinGame(Player player){
        //TO-DO: IMPLEMENTAR LÓGICA PARA ADICIONAR PLAYER E/OU MOVIMENTA-LO


        return game;
    };


    @MessageMapping("/createGame")
    @SendTo("/topic/game")
    private Game handleGameInitializer(Player player){
        game = new Game(player);
        return game;
    }
}
