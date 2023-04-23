package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplorationMap {
    private int numVisitedCells = 0;
    private final Cell[][] matrix;



    public ExplorationMap(int n) {
        this.matrix = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matrix[i][j] = new Cell();
                this.matrix[i][j].setVisited(false);
            }
        }


    }

    public Cell[][] getMatrix() {
        return matrix;
    }


    public boolean visit(int row, int col, Robot robot) {
        if (numVisitedCells == matrix.length * matrix[0].length){
            robot.setRunning(false);
            }
        synchronized (matrix[row][col]) {
            if (!matrix[row][col].isVisited()) {
                List<Token> tokens = robot.extractTokens(3);
                matrix[row][col].setTokens(tokens);
                matrix[row][col].setVisited(true);
                numVisitedCells++;
                System.out.println(robot.getName() + ":Successfully visited cell (" + row + ", " + col + ")");


                return true;
            } else {

                System.out.println(robot.getName() + ":Cell (" + row + ", " + col + ") has already been visited");


                return false;

            }
        }
    }

    public int getNumVisitedCells() {
        return numVisitedCells;
    }

    public void setNumVisitedCells(int numVisitedCells) {
        this.numVisitedCells = numVisitedCells;
    }

    @Override
    public String toString() {
        return "ExplorationMap{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
}