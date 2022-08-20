package ua.com.shestakova.Island.settingsActions;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class Time extends Thread {

    public static LocalDate startDay = LocalDate.now();
    public static int TIME_OF_GAME = 5;

    {
        System.out.println("Счетчик времени запущен");
    }

    public void run() {
        for (int i = 0; i < TIME_OF_GAME; i++) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(i);
        }
    }

}
