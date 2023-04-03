package org.game;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class Line implements Serializable {
    private Dot dot1;
    private Dot dot2;
    private Color color;
    private boolean selected;
    private Player player;

    public Line(Dot dot1, Dot dot2) {
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.selected = false;
        this.player = null;
        this.color = Color.gray;
    }

    public Dot getDot1() {
        return dot1;
    }

    public void setDot1(Dot dot1) {
        this.dot1 = dot1;
    }

    public Dot getDot2() {
        return dot2;
    }

    public void setDot2(Dot dot2) {
        this.dot2 = dot2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return dot1.equals(line.dot1) && dot2.equals(line.dot2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dot1, dot2);
    }
}