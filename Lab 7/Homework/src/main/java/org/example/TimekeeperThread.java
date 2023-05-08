package org.example;

public class TimekeeperThread extends Thread {
    private int timeLimit;
    private boolean isTimeUp;

    public TimekeeperThread(int timeLimit) {
        this.timeLimit = timeLimit;
        this.isTimeUp = false;
        setDaemon(true);
    }

    public boolean isTimeUp() {
        return isTimeUp;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Time elapsed: " + elapsedTime / 1000 + " seconds");
            if (elapsedTime >= timeLimit * 1000) {
                isTimeUp = true;
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        System.exit(0);
    }
}