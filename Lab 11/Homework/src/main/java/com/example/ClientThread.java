package com.example;

import com.example.entity.PlayerEntity;
import game.Game;
import game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private GameServer gameServer;

    private Player player;

    public ClientThread(Socket clientSocket, GameServer gameServer) {
        this.clientSocket = clientSocket;
        this.gameServer = gameServer;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            getPlayerName();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {

                System.out.println("Received command from client: " + inputLine);
                //   out.println("Server received the request: " + inputLine);
                if (inputLine.equals("create game")) {
                    Game game = gameServer.createGame(player);
                    out.println("Created game");
                    while (!game.isStart())
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    out.println("Game started");
                    play(game);
                } else if (inputLine.equals("join game")) {
                    List<Game> games = gameServer.getAvailableGames();
                    if (games.size() == 0) {
                        out.println("No available games");
                    } else {
                        out.println("Available games: " + games + " Choose a game to join");
                        String gameName = in.readLine();
                        Game game = gameServer.joinGame(player, gameName);
                        if (game == null) {
                            out.println("Invalid game name");
                        } else {
                            out.println("Joined game");
                            while (!game.isStart())
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            out.println("Game started");
                            play(game);
                        }

                    }


                } else if (inputLine.equals("stop")) {
                    out.println("Server stopped");
                    break;
                } else if (inputLine.equals("help")) {
                    getAllCommands();
                }
                else if (inputLine.equals("Send players.")) {
                    out.println("Players: " + gameServer.getRegisteredPlayers());
                }
                else {
                    out.println("Invalid command");
                }

            }

            in.close();
            out.close();
            clientSocket.close();
            System.exit(0);
        } catch (IOException e) {
            clientDisconnect();

        }
    }

    private void getAllCommands() {
        out.println("Available commands:-create game  -join game  -exit  -stop  -help");
    }

    private void play(Game game) {

        String inputLine;
        int index;
        out.println("You have 1 minute time.\nYou should choose a line and column to place your symbol.\n");
        if (player.equals(game.getPlayer1())) {
            out.println("You are player 1. Your symbol is X");
            player.setSymbol('X');
            index = 0;
        } else {
            out.println("You are player 2. Your symbol is O");
            player.setSymbol('O');
            index = 1;
        }
        while (true) {

            try {

                String boardState = game.getBoard().getBoard();
                //System.out.println(boardState);
                out.println(boardState);
                if (game.getCurrentPlayerIndex() == index) {
                    out.println("It's your turn. Choose a row and a column to place your symbol.");
//                    inputLine = in.readLine();
//                    if (inputLine.equals("exit")) {
//                        break;
//                    }
                    boolean valid = false;
                    while (!valid) {
                        try {
                            int row = Integer.parseInt(in.readLine());
                            int col = Integer.parseInt(in.readLine());
                            System.out.println("row: " + row + " col: " + col);
                            if (in.readLine().equals("submit move")) {
                                if (game.makeMove(row, col)) {
                                    valid = true;
                                    out.println("Move submitted");
                                } else
                                    out.println("Invalid move.Try again.");
                            }
                        } catch (NumberFormatException e) {
                            out.println("Invalid move.Try again.");
                        }

                    }
                    if (game.isDone()) {
                        out.println("Game ended!\nThe winner is " + game.getWinner().getName() + " with symbol " + game.getWinner().getSymbol() + "!");
                        break;
                    }
                } else {
                    out.println("It's your opponent's turn. Please wait.");
                    while (game.getCurrentPlayerIndex() != index)
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    if (game.isDone()) {
                        out.println("Game ended!\nThe winner is " + game.getWinner().getName() + " with symbol " + game.getWinner().getSymbol() + "!");
                        break;
                    }
                    if (game.isErr()) {

                        out.println("Your opponent disconnected.");
                        out.println("You won!");
                        break;
                    }
                }


            } catch (IOException e) {
                clientDisconnectGame(game);
                break;
            }
        }
    }

    private void clientDisconnectGame(Game game) {
        try {

            in.close();
            out.close();
            clientSocket.close();
            game.setErr(true);
            game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + 1) % 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clientDisconnect() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPlayerName() {
        try {
            out.println("Enter your name: ");
            String name = in.readLine();
            player = new Player(name);
            gameServer.getRegisteredPlayers().add(player);
            PlayerEntity playerEntity =new PlayerEntity(player.getId(),player.getName());
            gameServer.getGameRepository2().createPlayer(playerEntity.getPlayerId(), player.getName());
            System.out.println("Player " + name + " connected");

            out.println("Welcome " + name + "!");
        } catch (IOException e) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

