package com.jzpacheco.bombermanrmk.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Game {
    private int [][] map;
    private List<Player> players;
    public Game(){}

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Game(int[][] map, List<Player> players) {
        this.map = map;
        this.players = players;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Arrays.deepEquals(map, game.map) && Objects.equals(players, game.players);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(players);
        result = 31 * result + Arrays.deepHashCode(map);
        return result;
    }
}
