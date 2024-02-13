package com.jzpacheco.bombermanrmk.services;

import com.jzpacheco.bombermanrmk.model.Game;
import com.jzpacheco.bombermanrmk.model.Player;
import com.jzpacheco.bombermanrmk.repositories.GameRepository;
import com.jzpacheco.bombermanrmk.request.PlayerJoinRequest;

import java.util.List;

public class GameService {
    private GameRepository repository;

    public Game findByPassword(String password){
        return  repository.findByPassword(password);
    }

    public List<Game> findAll(){
        return repository.findAll();
    }
    public Game create(Game game){
       return repository.postPlayer(game);
    }

    public Game join(PlayerJoinRequest request){
        Game game = repository.findByPassword(request.getPassword());

        game.handlePlayer(request.getPlayer());
        return game;
    }

}
