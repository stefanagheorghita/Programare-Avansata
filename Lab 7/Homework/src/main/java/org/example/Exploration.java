package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exploration {
    private final SharedMemory mem;
    private final ExplorationMap map;
    private final List<Robot> robots = new ArrayList<>();
    TimekeeperThread timekeeperThread;

    public Exploration(int n) {
        mem = new SharedMemory(n);
        map = new ExplorationMap(n);
        timekeeperThread = new TimekeeperThread(30);
    }


    public void start() {
//        int timeLimit = 20;
//        TimekeeperThread timekeeperThread = new TimekeeperThread(timeLimit);
        timekeeperThread.start();
        for (Robot robot : robots) {
            robot.setExplore(this);
            Thread t = new Thread(robot);
            t.start();
        }

        while(true){
            if(verify()){
                System.out.println("All cells have been visited");
                for(Robot robot : robots)
                {
                    System.out.println(robot.getName() + " has extracted " + robot.getNumberOfTokens() + " tokens");
                }
                System.exit(0);
            }

        }


    }

    private boolean verify() {
        for(Robot robot : robots)
            if(robot.isRunning() == true)
                return map.getNumVisitedCells() == map.getMatrix().length * map.getMatrix()[0].length || !timekeeperThread.isAlive();
            return true;

    }

    void addRobot(Robot robot) {
        for (Robot r : robots)
            if (r.getName().equals(robot.getName())) {
                System.out.println("There already exists a robot with the name " + robot.getName() + ".");
                System.exit(1);
            }
        robots.add(robot);
    }

    public SharedMemory getMem() {
        return mem;
    }

    public ExplorationMap getMap() {
        return map;
    }

    public List<Robot> getRobots() {
        return robots;
    }


    public void pauseAll() {
        System.out.println("Pausing all robots");
        for (Robot robot : robots) {
            synchronized (robot) {
                robot.pause();
            }
        }

    }

    public void pauseAllTime(int num) {
        System.out.println("Pausing all robots for " + num + " seconds");
        for (Robot robot : robots) {
            synchronized (robot) {
                robot.pause();
            }
        }
//        try {
//            Thread.sleep(num * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        for (Robot robot : robots) {
            synchronized (robot) {
                robot.resume();
            }
        }
    }

    public void pauseRobot(String name) {
        for (Robot robot : robots)
            if (robot.getName().equals(name)) {
                synchronized (robot) {
                    robot.pause();
                }
                return;
            }
        System.out.println("There is no robot with the name " + name + ".");
    }

    public void pauseRobotTime(String name, int time) {
        for (Robot robot : robots) {
            if (robot.getName().equals(name)) {
                synchronized (robot) {
                    robot.setTimeToSleep(time);
                    robot.pause();
                }
            }
        }
    }

    public void resumeAll() {
        for (Robot robot : robots)
            robot.resume();
    }

    public void resumeRobot(String name) {
    }
}