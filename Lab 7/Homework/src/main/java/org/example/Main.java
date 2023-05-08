package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        var explore = new Exploration(4);
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
        explore.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(explore.timekeeperThread.isTimeUp()) break;
            String command = scanner.nextLine();
            String[] words = command.split("\\s+");
            if (words[0].equalsIgnoreCase("p")) {
                if (words.length == 1) explore.pauseAll();
                else if (words.length == 2) try {
                    int num = Integer.parseInt(words[1]);
                    explore.pauseAllTime(num);
                } catch (NumberFormatException e) {
                    explore.pauseRobot(words[1]);
                }
                else if (words.length == 3) {
                    try {
                        int num = Integer.parseInt(words[2]);
                        explore.pauseRobotTime(words[1],num);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please try again.");
                    }
                } else System.out.println("Invalid input. Please try again.");

            } else if (words[0].equalsIgnoreCase("s")) {
                if (words.length == 1) explore.resumeAll();
                else if (words.length == 2) explore.resumeRobot(words[1]);
                else System.out.println("Invalid input. Please try again.");
            } else {
                System.out.println("Invalid input. Please try again.");
            }

        }
    }
}
