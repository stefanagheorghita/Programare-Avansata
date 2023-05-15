package com.example;

import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class TakePlayers {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8099;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public TakePlayers() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TakePlayers takePlayers = new TakePlayers();
        takePlayers.start();
    }

    public String start() {

        try {
            String response1 = in.readLine();
            out.println("Fake client ");
            String response = in.readLine();
            out.println("Send players.");
            response = in.readLine();
            System.out.println("Server response: " + response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
