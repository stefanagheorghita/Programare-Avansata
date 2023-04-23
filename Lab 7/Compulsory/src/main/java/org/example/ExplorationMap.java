package org.example;

import java.util.Arrays;
import java.util.List;

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
        if (numVisitedCells == matrix.length * matrix[0].length)
            robot.setRunning(false);
        synchronized (matrix[row][col]) {
            if (!matrix[row][col].isVisited()) {
                List<Token> tokens = robot.extractTokens(matrix.length);
                matrix[row][col].setTokens(tokens);
                matrix[row][col].setVisited(true);
                numVisitedCells++;
                System.out.println(robot.getName()+":Successfully visited cell (" + row + ", " + col + ")");
                return true;
            } else {
                System.out.println(robot.getName()+":Cell (" + row + ", " + col + ") has already been visited");
                return false;
            }
        }
    }


    @Override
    public String toString() {
        return "ExplorationMap{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
}