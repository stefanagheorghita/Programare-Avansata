package org.game;

import org.example.DrawingPanel;
import org.example.MainFrame;

import java.io.Serializable;

public class Game implements Serializable {

    private Board board;
    private Player currentPlayer;

    private Player winner;

    private Player player1, player2;


    public Game(int width, int height, Player player1, Player player2) {

        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(width, height);
        this.currentPlayer = player1;
        this.winner = null;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public boolean triangle(Dot d1, Dot d2) {
        for (Dot dot : d1.getAdjDots().keySet())
            if (d1.getAdjDots().get(dot).equals(currentPlayer.getColor()))
                if (d2.getAdjDots().get(dot)!=null && d2.getAdjDots().get(dot).equals(currentPlayer.getColor()))
                    return true;
        return false;
    }

    public boolean finish() {
        for (Dot dot : board.getDots())
            if (dot.freeDots())
                return false;
        return true;

    }


    public void resetDrawingPanel(DrawingPanel drawingPanel) {
        drawingPanel.setNumVertices(board.getDots().size());
            drawingPanel.remake(board.getDots());

    }

}
