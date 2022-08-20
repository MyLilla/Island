package ua.com.shestakova.Island.settingsActions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Threads {

    public static void startTime() {
        Time thread = new Time();
        thread.setDaemon(true);
        thread.start();
    }

    public static void startDay() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < Time.TIME_OF_GAME; i++) {
            executorService.submit(new LifeTime());
        }
        try {
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();

    }
}
