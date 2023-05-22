package game;

import com.example.GameTimer;


public class Game {

    static int x=1;
    private Board board;
    private Player[] players = new Player[2];
    private int currentPlayerIndex;

    private int winnerIndex;

    private boolean start = false;

    private boolean done = false;

    private boolean err = false;

    private GameTimer[] timers = new GameTimer[2];

    private int[] timeLeft = new int[2];

    private String id;


    public Game(int boardSize) {
        id= String.valueOf("game"+ x);
        this.board = new Board(boardSize);
        this.currentPlayerIndex = 0;
        x++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean makeMove(int row, int col) {
        if(row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize())
            return false;
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
        timeLeft[0] = 60;
        timeLeft[1] = 60;
        timers[0].startTimer(() -> {
            if (currentPlayerIndex == 0) {
                System.out.println("Time left for player 1: " + timeLeft[0]);
                timeLeft[0]--;
                if (timeLeft[0] <= 0) {
                    handlePlayerTimeout(0);
                }
            }
        });
        timers[1].startTimer(() -> {
            System.out.println("Time left for player 2: " + timeLeft[1]);
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
        timers[0].stopTimer();
        timers[1].stopTimer();
        currentPlayerIndex = (index + 1) % 2;
        //Thread.currentThread().interrupt();
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

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                '}';
    }
}
