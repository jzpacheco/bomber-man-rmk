package com.jzpacheco.bombermanrmk.services;

import com.jzpacheco.bombermanrmk.model.Game;
import com.jzpacheco.bombermanrmk.model.Player;
import com.jzpacheco.bombermanrmk.repositories.GameRepository;
import com.jzpacheco.bombermanrmk.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GameService {
    private GameRepository repository;

    public Game findById(Integer id){
        return  repository.findById(id);
    }

    public List<Game> findAll(){
        return repository.findAll();
    }
    public Game create(Game game){
       return repository.postPlayer(game);
    }

    public Game join(String password){
        return repository.findByPassword(password);
    }

}
