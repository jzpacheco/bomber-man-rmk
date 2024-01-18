package com.jzpacheco.bombermanrmk.model;

import java.util.*;

public class Game {
    private String password;
    private int [][] map;
    private List<Player> players = new ArrayList<>();
    private static final int X = 21;
    private static final int Y = 41;
    public Game(){}

    public Game(Player player) {
        this.password = generatePassword();
        this.players.add(player);
        this.map = createMap();
    }

    private String generatePassword() {
        String uuid = UUID.randomUUID().toString();

        return uuid.replaceAll("-","").substring(0,6);
    }


    private int[][] createMap(){

        int[][] newMap = new int[X][];

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
