package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

public class GameClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8099;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public GameClient() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GameClient gameClient = new GameClient();
        gameClient.start();
    }

    public void start() {
        try {
            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to server. Type 'exit' to quit.\nType 'help' to see the available commands.");
            giveName();
            String inputLine;
            while (true) {

                System.out.println("Enter a command:");
                inputLine = keyboardInput.readLine();
                out.println(inputLine);
                if (inputLine.equals("exit")) {
                    break;
                }

                String response = in.readLine();
                System.out.println("Server response: " + response);
                if (response.equals("Server stopped")) {
                    break;
                }

                if (response.equals("Created game")) {
                    System.out.println("Game created. Waiting for opponent...");
                    response = in.readLine();
                    System.out.println(response);
                    if (response.equals("Game started"))
                        play();
                } else if (response.startsWith("Available games:")||response.startsWith("No available games")) {
                    if (response.equals("No available games.")) {
                        continue;
                    }
                    String gameName = keyboardInput.readLine();
                    System.out.println("You chose game " + gameName+".");
                    out.println(gameName);
                    String verify = in.readLine();
                    System.out.println(verify);
                    if (verify.equals("Invalid game name")) {
                        continue;
                    }

                    response = in.readLine();
                    System.out.println(response);
                    if (response.equals("Game started"))
                        play();
                }
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void play() throws IOException {
        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        String firstMessage = in.readLine();
        System.out.println(firstMessage);
        String playerType = in.readLine();
        System.out.println(playerType);
        while (true) {
            String response = in.readLine();
            System.out.println(response);
            if (response.equals("Game ended!") || response.startsWith("Your opponent disconnected.")) {
                break;
            }
            if (response.startsWith("It's your turn.")) {
                do {
                    try {

                        System.out.println("Row: ");
                        int row = Integer.parseInt(keyboardInput.readLine());
                        System.out.println("You chose row " + row);
                        //out.println(row);
                        System.out.println("Column: ");
                        int col = Integer.parseInt(keyboardInput.readLine());
                        System.out.println("You chose column " + col);
                        System.out.println("Insert 'submit move' to send the coordinates.");
                        // out.println(col);
                        String send = keyboardInput.readLine();
                        while (!send.equals("submit move")) {

                            System.out.println("Try again. Choose a row and a column.");
                            System.out.println("Row: ");
                            row = Integer.parseInt(keyboardInput.readLine());
                            System.out.println("You chose row " + row);
                            System.out.println("Column: ");
                            col = Integer.parseInt(keyboardInput.readLine());
                            System.out.println("You chose column " + col);
                            System.out.println("Insert 'submit move' to send the coordinates.");
                            send = keyboardInput.readLine();

                        }
                        out.println(row);
                        out.println(col);
                        out.println(send);
                        response = in.readLine();
                        System.out.println(response);
                        if (response.equals("Game ended!")) {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        response = "Invalid move.Try again.";
                        System.out.println(response);
                    }
                } while (response.equals("Invalid move.Try again."));

            }
        }
        String finalResponse = in.readLine();
        System.out.println(finalResponse);
    }

    private void getAllCommands() {
        System.out.println("Available commands:");
        System.out.println("create game");
        System.out.println("join game");
        System.out.println("submit move");
        System.out.println("exit");
        System.out.println("stop");
    }

    private void giveName() throws IOException {
        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(in.readLine());
        try {
            out.println(keyboardInput.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String response = in.readLine();
        System.out.println(response);
    }
}
