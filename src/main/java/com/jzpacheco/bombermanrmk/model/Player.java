package com.jzpacheco.bombermanrmk.model;

import java.util.Objects;

public class Player {
    private Integer id;
    private String name;
    private Integer x;
    private Integer y;

    public Player() {
    }
    public Player(String name){
        this.name = name;
    }

    public Player(Integer id, String name, Integer x, Integer y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(name, player.name) && Objects.equals(x, player.x) && Objects.equals(y, player.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, x, y);
    }
}
