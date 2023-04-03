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
        System.out.println(this.name + ": Start position: " + initialX + " " + initialY);
        while (running) {
            Random random = new Random();

//           int row= random.nextInt(explore.getMap().getMatrix().length);
//           int col= random.nextInt(explore.getMap().getMatrix()[1].length);
            int row, col;
            if (initialX == 0)
                row = random.nextInt(initialX, initialX + 2);
            else if (initialX == explore.getMap().getMatrix().length - 1)
                row = random.nextInt(initialX - 1, initialX+1);
            else
                row = random.nextInt(initialX - 1, initialX + 2);
            if (initialY == 0)
                col = random.nextInt(initialY, initialY + 2);
            else if (initialY == explore.getMap().getMatrix()[1].length - 1)
                col = random.nextInt(initialY - 1, initialY+1);
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
        initialX = random.nextInt(explore.getMap().getMatrix().length);
        initialY = random.nextInt(explore.getMap().getMatrix()[1].length);
        while (explore.getMap().getMatrix()[initialX][initialY].isVisited()) {
            initialX = random.nextInt(explore.getMap().getMatrix().length);
            initialY = random.nextInt(explore.getMap().getMatrix()[1].length);
        }
        explore.getMap().getMatrix()[initialX][initialY].setVisited(true);
    }
}