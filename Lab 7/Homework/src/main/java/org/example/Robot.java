package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Robot implements Runnable {
    private String name;
    int initialX, initialY;
    private boolean running;
    Exploration explore;
    private boolean paused;
    private int timeToSleep;

    private int numberOfTokens = 0;


    public Robot(String name) {
        this.name = name;
        running = true;
        paused = false;
        timeToSleep = -10;
    }

    public void run() {
        start();

        while (running) {

            while (!paused) {

                if (!CellsToVisit()) {
                    running = false;
                    break;
                }
                Random random = new Random();
                int row, col;
//                if (initialX == 0)
//                    row = random.nextInt(initialX, initialX + 2);
//                else if (initialX == explore.getMap().getMatrix().length - 1)
//                    row = random.nextInt(initialX - 1, initialX + 1);
//                else
//                    row = random.nextInt(initialX - 1, initialX + 2);
//                if (initialY == 0)
//                    col = random.nextInt(initialY, initialY + 2);
//                else if (initialY == explore.getMap().getMatrix()[1].length - 1)
//                    col = random.nextInt(initialY - 1, initialY + 1);
//                else
//                    col = random.nextInt(initialY - 1, initialY + 2);

                row = systematicSearch()[0];
                col = systematicSearch()[1];
                if (row == -1 && col == -1) {
                    running = false;
                    break;
                }
                if (explore.getMap().visit(row, col, this)) {
                    initialX = row;
                    initialY = col;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (explore.timekeeperThread.isTimeUp()) {
                    running = false;
                    break;
                }

            }
            if (this.paused) {
                if (timeToSleep == -10)
                    while (paused)
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                else {
                    try {
                        Thread.sleep(timeToSleep * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resume();
                }
            }
        }
       // System.out.println(this.name + " extracted " + numberOfTokens + " tokens.");
        System.out.println(this.name + " M-am oprit.");
    }


    public int[] systematicSearch() {
        int row, col;
        int xMax = 0, yMax = 0;
        int maxFreeNeighbours = -1;
        if (!CellsToVisit()) {
            return new int[]{-1, -1};
        }
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                row = initialX + i;
                col = initialY + j;
                if (isValid(row, col)) {
                    if (findFreeNeighbours(row, col) > maxFreeNeighbours) {
                        maxFreeNeighbours = findFreeNeighbours(row, col);
                        xMax = row;
                        yMax = col;
                    }

                }
            }
        int[] position = new int[2];
        position[0] = xMax;
        position[1] = yMax;
        return position;
    }

    public int findFreeNeighbours(int row, int col) {
        int freeNeighbours = 0;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (isValid(row + i, col + j)) {
                    freeNeighbours++;
                }
            }
        return freeNeighbours;
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
        numberOfTokens += number;
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
    public void pause() {

        synchronized (this) {
            System.out.println(name + " is paused");
            this.paused = true;

        }

    }

    public void resume() {
        synchronized (this) {
            timeToSleep = -10;
            paused = false;
            System.out.println(name + " is resumed");
        }
    }

    public void setExplore(Exploration explore) {
        this.explore = explore;
    }

    public void setTimeToSleep(int timeToSleep) {
        this.timeToSleep = timeToSleep;
    }


    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public void setNumberOfTokens(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
    }
}