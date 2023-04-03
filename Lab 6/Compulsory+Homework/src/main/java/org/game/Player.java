package org.game;

import java.awt.*;
import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private Color color;


    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                '}';
    }
}
