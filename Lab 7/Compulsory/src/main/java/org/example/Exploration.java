package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private final SharedMemory mem;
    private final ExplorationMap map;
    private final List<Robot> robots = new ArrayList<>();

    public Exploration(int n) {
        mem=new SharedMemory(n);
        map=new ExplorationMap(n);
    }

    public void start() {
        for (Robot robot : robots) {
            robot = new Robot(robot.getName(), this);
            Thread t = new Thread(robot);
            t.start();
        }
    }

    void addRobot(Robot robot) {
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


}