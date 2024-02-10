package com.jzpacheco.bombermanrmk.request;

import com.jzpacheco.bombermanrmk.model.Player;

public class PlayerJoinRequest {
    private String password;
    private Player player;

    public PlayerJoinRequest(){}

    public PlayerJoinRequest(String password, Player player) {
        this.password = password;
        this.player = player;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
