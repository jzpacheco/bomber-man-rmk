package com.jzpacheco.bombermanrmk.controllers;

import com.jzpacheco.bombermanrmk.model.Game;
import com.jzpacheco.bombermanrmk.model.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class GameController {

    private HashMap<String,Game> games = new HashMap<>();

    @MessageMapping("/joinGame")
    @SendTo("/topic/game")
    public Game handlePlayerJoinGame(String password){
        Game game = games.get(password);
        game.addPlayer(new Player());
        return game;
    };


    @MessageMapping("/createGame")
    @SendTo("/topic/game")
    private Game handleGameInitializer(String userName){
        Player player = new Player(userName);
        Game game = new Game();
        game.addPlayer(player);

        games.put(game.getPassword(), game);
        return game;
    }
}
