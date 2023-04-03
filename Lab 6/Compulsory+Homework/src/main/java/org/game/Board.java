package org.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {
    int width;
    int height;
    private List<Dot> dots;
    private List<Line> lines;
    private Dot selected;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.dots = new ArrayList<>();
        this.lines = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Dot> getDots() {
        return dots;
    }

    public void setDots(List<Dot> dots) {
        this.dots = dots;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void addDot(Dot dot) {
        dots.add(dot);
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public Dot getDotObj(double x, double y) {
        for (Dot dot : dots) {
            if (x >= dot.getX() - 10 && x <= dot.getX() + 10)
                if (y >= dot.getY() - 10 && y <= dot.getY() + 10) {
                    return dot;
                }
        }
        return null;
    }

    public void resetDots() {
        dots = new ArrayList<>();
    }

    public void resetLines() {
        lines = new ArrayList<>();
    }

    public Dot getSelected() {
        return selected;
    }

    public void setSelected(Dot selected) {
        this.selected = selected;
    }


}