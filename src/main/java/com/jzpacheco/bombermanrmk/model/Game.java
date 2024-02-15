package com.jzpacheco.bombermanrmk.model;

import java.util.*;

public class Game {
    private String password;
    private int [][] map;
    private Map<Integer, Player> players = new HashMap<>();


    private  int x = 21;
    private  int y = 41;
    public Game(){
        this.password = generatePassword();
        this.map = createMap();
    }

    public Game(Player player) {
        this.password = generatePassword();
        this.players.put(player.getId(), player);
        this.map = createMap();
    }

    public void handlePlayer(Player player) {

        if (players.size() <= 4 && !players.containsKey(player.getId())) {
            player.setId(players.size() + 1);

            switch (players.size()) {
                case 0 -> {
                    player.setX(0);
                    player.setY(0);
                }
                case 1 -> {
                    player.setX(x - 1);
                    player.setY(x - 1);
                }
                case 2 -> {
                    player.setX(0);
                    player.setY(y - 1);
                }
                case 3 -> {
                    player.setX(x - 1);
                    player.setY(0);
                }
            }

            players.put(player.getId(), player);
        }
    }

    private String generatePassword() {
        String uuid = UUID.randomUUID().toString();

        return uuid.replaceAll("-","").substring(0,6);
    }


    private int[][] createMap(){

        int[][] newMap = new int[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (isFixedObstacle(i, j)){
                    newMap[i][j] = 2;
                } else if (isInitialPosition(i, j)){
                    newMap[i][j] = 0;
                } else {
                    newMap[i][j] = emptyOrWall(i,j);
                }
            }
        }
        return newMap;
    }

    private boolean isFixedObstacle(int i, int j) {
        return i % 2 != 0 && j % 2 != 0;
    }

    private boolean isInitialPosition(int i, int j) {
        return (i <= 1 && j <= 1) || (i <= 1 && j >= Y - 2) || (i >= X - 2 && j <= 1) || (i >= X - 2 && j >= Y - 2);
    }

    private int emptyOrWall(int i, int j){
        Random random = new Random();
        return random.nextInt(2);
    }

    public String getPassword() {
        return password;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(password, game.password) && Arrays.deepEquals(map, game.map) && Objects.equals(players, game.players);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(password, players);
        result = 31 * result + Arrays.deepHashCode(map);
        return result;
    }
}
