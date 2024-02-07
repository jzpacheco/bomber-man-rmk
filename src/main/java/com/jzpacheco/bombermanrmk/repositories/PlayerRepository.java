package com.jzpacheco.bombermanrmk.repositories;

import com.jzpacheco.bombermanrmk.model.Player;

import java.util.HashMap;
import java.util.List;

public class PlayerRepository {

    private HashMap<Integer, Player> players = new HashMap<>();

    public Player findById(Integer id){
        return  players.get(id);
    }

    public Player postPlayer(Player player){
        players.put(player.getId(), player);

        return players.get(player.getId());
    }

    public List<Player> findAll(){
        return players.values().stream().toList();
    }



}
