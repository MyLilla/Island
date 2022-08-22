package ua.com.shestakova.Island.settingsActions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Threads {   // я думаю пока что делать тут

    // сделать карту животных по классу
    // отправлять на 10 потоков каждый класс


    public static void startDay() { // задание

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < Time.timeOfGame; i++) {        // сколько раз запустится
            executorService.submit(new LifeTime());          //  Run() задача

        }
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();

    }
}
