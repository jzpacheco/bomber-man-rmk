package com.jzpacheco.bombermanrmk.model;

import java.util.*;

public class Game {
    private int [][] map;
    private List<Player> players;
    public Game(){}


    
    private int[][] createMap(){
        Map<String, Integer> mapSize = new HashMap<>();
        mapSize.put("x",21);
        mapSize.put("y",41);

        for (int i = 0; i < mapSize.get("x"); i++) {
            for (int j = 0; j < mapSize.get("y"); j++) {
                if (i % 2 != 0 && j%2 != 0){
                    map[i][j] = 2;
                } else if ((i <= 1 && j <= 1) || (i <= 1 && j >= mapSize.y - 2) || (i >= mapSize.x - 2 && j <= 1) || (i >= mapSize.x - 2 && j >= mapSize.y - 2)
                 {

                }

            }
            
        }
    }

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
