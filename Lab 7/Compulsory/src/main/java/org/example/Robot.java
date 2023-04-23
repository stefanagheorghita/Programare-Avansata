package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot implements Runnable {
    private String name;
    int initialX, initialY;
    private boolean running;
    Exploration explore;

    public Robot(String name, Exploration explore) {
        this.name = name;
        running = true;
        this.explore = explore;

    }

    public Robot(String name) {
        this.name = name;
    }

    public void run() {
        start();

        while (running) {
            if (!CellsToVisit()) {
                running = false;
                break;
            }
            Random random = new Random();
            int row, col;
            if (initialX == 0)
                row = random.nextInt(initialX, initialX + 2);
            else if (initialX == explore.getMap().getMatrix().length - 1)
                row = random.nextInt(initialX - 1, initialX + 1);
            else
                row = random.nextInt(initialX - 1, initialX + 2);
            if (initialY == 0)
                col = random.nextInt(initialY, initialY + 2);
            else if (initialY == explore.getMap().getMatrix()[1].length - 1)
                col = random.nextInt(initialY - 1, initialY + 1);
            else
                col = random.nextInt(initialY - 1, initialY + 2);
            if (explore.getMap().visit(row, col, this)) {
                initialX = row;
                initialY = col;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(this.name + " M-am oprit.");

    }

    private boolean CellsToVisit() {
        int row, col;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                row = initialX + i;
                col = initialY + j;
                if (isValid(row, col)) {
                    return true;
                }
            }
        return false;
    }

    public boolean isValid(int row, int col) {
        Cell[][] matrix = explore.getMap().getMatrix();
        if (row < 0 || row > matrix.length - 1 || col < 0 || col > matrix.length - 1)
            return false;
        return !matrix[row][col].isVisited();

    }

    public List<Token> extractTokens(int number) {
        return explore.getMem().extractTokens(number);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private void start() {
        Random random = new Random();
        int dimension=explore.getMap().getMatrix().length;
        initialX = random.nextInt(dimension);
        initialY = random.nextInt(dimension);
        while (explore.getMap().getMatrix()[initialX][initialY].isVisited()) {
            initialX = random.nextInt(dimension);
            initialY = random.nextInt(dimension);
        }
        System.out.println(this.name + ": Start position: " + initialX + " " + initialY);
        explore.getMap().visit(initialX, initialY, this);
    }
}