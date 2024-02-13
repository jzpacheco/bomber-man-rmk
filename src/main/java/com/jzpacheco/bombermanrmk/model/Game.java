package com.jzpacheco.bombermanrmk.model;

import java.util.*;

public class Game {
    private String password;
    private int [][] map;
    private List<Player> players = new ArrayList<>();


    private  int x = 21;
    private  int y = 41;
    public Game(){
        this.password = generatePassword();
        this.map = createMap();
    }

    public Game(Player player) {
        this.password = generatePassword();
        this.players.add(player);
        this.map = createMap();
    }

    public void handlePlayer(Player player){
        if (players.size() <= 4) {
            player.setId(players.size()+1);
            if (players.isEmpty()) {
                player.setX(0);
                player.setY(0);
            } else if (players.size() == 1) {
                player.setX(x - 1);
                player.setY(x - 1);
            } else if (players.size() == 2) {
                player.setX(0);
                player.setY(y - 1);
            } else if (players.size() == 3) {
                player.setX(x - 1);
                player.setY(0);
            }
            players.add(player);
        }
    }

    private String generatePassword() {
        String uuid = UUID.randomUUID().toString();

        return uuid.replaceAll("-","").substring(0,6);
    }


    private int[][] createMap(){

        int[][] newMap = new int[X][Y];

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
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
