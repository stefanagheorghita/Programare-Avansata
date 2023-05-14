package org.game;

import org.example.GameTimer;

public class Game {
    private Board board;
    private Player[] players = new Player[2];
    private int currentPlayerIndex;

    private int winnerIndex;

    private boolean start = false;

    private boolean done = false;

    private boolean err = false;

    private GameTimer[] timers = new GameTimer[2];

    private int[] timeLeft = new int[2];


    public Game(int boardSize) {
        this.board = new Board(boardSize);
        this.currentPlayerIndex = 0;
    }

    public Game(int boardSize, Player player1, Player player2) {
        this.board = new Board(boardSize);
        this.players = new Player[]{player1, player2};
        this.currentPlayerIndex = 0;
    }

    public boolean makeMove(int row, int col) {
        if (board.isValid(row, col)) {
            Player currentPlayer = players[currentPlayerIndex];
            board.placeMove(row, col, currentPlayer.getSymbol());

            if (board.isWinningMove(row, col, currentPlayer.getSymbol())) {
                winnerIndex = currentPlayerIndex;
                done = true;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            return true;
        }
        return false;
    }

    public boolean hasSetPlayers() {
        return players[0] != null && players[1] != null;
    }

    public void setPlayers(int index, Player player) {
        players[index] = player;
    }

    public void start() {
        start = true;
        timers[0] = new GameTimer(1);
        timers[1] = new GameTimer(1);
        timeLeft[0] = 10;
        timeLeft[1] = 10;
        timers[0].startTimer(() -> {
            if (currentPlayerIndex == 0) {
                timeLeft[0]--;
                if (timeLeft[0] <= 0) {
                    handlePlayerTimeout(0);
                }
            }
        });
        timers[1].startTimer(() -> {
            if (currentPlayerIndex == 1) {
                timeLeft[1]--;
                if (timeLeft[1] <= 0) {
                    handlePlayerTimeout(1);
                }
            }
        });


    }

    private void handlePlayerTimeout(int index) {
        winnerIndex = (index + 1) % 2;
        done = true;
    }

    public boolean isStart() {
        return start;
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2(Player[] players) {
        return players[1];
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Player getWinner() {
        return players[winnerIndex];
    }

    public boolean isErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }


}
