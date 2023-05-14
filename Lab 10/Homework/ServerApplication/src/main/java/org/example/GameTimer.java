package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer {
    private ScheduledExecutorService executorService;
    private Runnable timerTask;
    private int intervalSeconds;

    public GameTimer(int intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void startTimer(Runnable task) {
        timerTask = task;
        executorService.scheduleAtFixedRate(task, 0, intervalSeconds, TimeUnit.SECONDS);
    }

    public void stopTimer() {
        executorService.shutdown();
    }
}
