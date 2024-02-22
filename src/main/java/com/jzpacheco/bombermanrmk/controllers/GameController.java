package com.jzpacheco.bombermanrmk.controllers;

import com.jzpacheco.bombermanrmk.model.Game;
import com.jzpacheco.bombermanrmk.model.Player;
import com.jzpacheco.bombermanrmk.request.PlayerJoinRequest;
import com.jzpacheco.bombermanrmk.services.GameService;
import com.jzpacheco.bombermanrmk.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

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
        return gameService.join(request);
    };
//To-DO: Test player api and joinGame refactor?

    @MessageMapping("/createGame")
    @SendTo("/topic/game")
    private Game handleGameInitializer(Integer playerId){
        Player player = playerService.findById(playerId);
        Game game = new Game();
        game.handlePlayer(player);
        return game;
    }
}
