package com.jzpacheco.bombermanrmk.controllers;

import com.jzpacheco.bombermanrmk.model.Game;
import com.jzpacheco.bombermanrmk.model.Player;
import com.jzpacheco.bombermanrmk.request.PlayerJoinRequest;
import com.jzpacheco.bombermanrmk.services.GameService;
import com.jzpacheco.bombermanrmk.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class GameController {

    private GameService gameService;

    private PlayerService playerService;

    @Autowired
    public GameController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;

    }

    @MessageMapping("/joinGame")
    @SendTo("/topic/game")
    public Game handlePlayerJoinGame(PlayerJoinRequest request){
        Game game = gameService.join(request.getPassword());

        //TO-DO
        game.addPlayer(new Player());
        return game;
    };


    @MessageMapping("/createGame")
    @SendTo("/topic/game")
    private Game handleGameInitializer(Integer playerId){
        Player player = playerService.findById(playerId);
        Game game = new Game();
        game.addPlayer(player);

        games.put(game.getPassword(), game);
        return game;
    }
}
