package org.game;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dot implements Serializable {
    private int x;
    private int y;
    private boolean selected;
    public int full=0;

    private Map<Dot, Color> adjDots = new HashMap<>();



    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
        this.selected = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean verifyAdj(Dot d) {
        for (Dot dot : adjDots.keySet()) {
            if (dot.equals(d))
                return true;

        }
        return false;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void addAdjDot(Dot dot){
        adjDots.put(dot,Color.cyan);
    }



    public boolean freeDots(){
        if(adjDots.size()==full)
            return false;
        return true;
    }

    public Map<Dot, Color> getAdjDots() {
        return adjDots;
    }

    public void setAdjDots(Map<Dot, Color> adjDots) {
        this.adjDots = adjDots;
    }
}