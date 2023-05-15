package com.example;

import com.example.controller.PlayerController;
import game.Game;
import game.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class GameServer {

    private List<Player> registeredPlayers = new ArrayList<>();

    private static final int PORT = 8099;

    public List<Game> games = new ArrayList<>();



    public void main(String[] args) {
        createServerSocket();
    }

    public void createServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public synchronized Game createGame(Player player) {
        Game newGame = new Game(10);
        newGame.setPlayers(0, player);
        games.add(newGame);
        return newGame;
    }

    public synchronized Game joinGame(Player player, String gameName) {

        for (Game game : games) {
            if (game.getId().equals(gameName)) {
                game.setPlayers(1, player);
                game.start();
                return game;
            }
        }
        return null;
    }

    public synchronized List<Game> getAvailableGames() {
        List<Game> availableGames = new ArrayList<>();
        for (Game game : games) {
            if (!game.hasSetPlayers()) {
                availableGames.add(game);
            }
        }
        return availableGames;
    }


    public synchronized boolean registerPlayer(Player player) {
        if (registeredPlayers.contains(player)) {
            return false;
        }
        registeredPlayers.add(player);
        return true;
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }

    public String sendRegisteredPlayers() {
        System.out.println(registeredPlayers);
        return registeredPlayers.toString();
    }
}
