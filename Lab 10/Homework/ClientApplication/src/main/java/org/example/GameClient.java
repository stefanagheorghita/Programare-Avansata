package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8099;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to server. Type 'exit' to quit.");

            String inputLine;
            while ((inputLine = keyboardInput.readLine()) != null) {
                out.println(inputLine);
                if (inputLine.equals("exit")) {
                    break;
                }
                String response = in.readLine();
                System.out.println("Server response: " + response);
                if(response.equals("Server stopped")) {
                    break;
                }
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
