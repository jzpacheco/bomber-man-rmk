package com.jzpacheco.bombermanrmk.services;

import com.jzpacheco.bombermanrmk.model.Player;
import com.jzpacheco.bombermanrmk.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlayerService {
    @Autowired
    private PlayerRepository repository;

    public Player findById(Integer id){
        return  repository.findById(id);
    }

    public List<Player> findAll(){
        return repository.findAll();
    }
    public Player create(Player player){
       return repository.postPlayer(player);
    }

}
