package com.jzpacheco.bombermanrmk.repositories;

import com.jzpacheco.bombermanrmk.model.Game;
import com.jzpacheco.bombermanrmk.model.Player;

import java.util.HashMap;
import java.util.List;

public class GameRepository {
    private HashMap<String, Game> games = new HashMap<>();

    public Game findByPassword(String password){
        return  games.get(password);
    }

    public List<Game> findAll(){
        return games.values().stream().toList();
    }

    public Game postPlayer(Game game){
        games.put(game.getPassword(), game);

        return games.get(game.getPassword());
    }




}
