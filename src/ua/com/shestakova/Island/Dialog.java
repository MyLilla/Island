package ua.com.shestakova.Island;

import ua.com.shestakova.Island.settings.Island;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Dialog {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        Island island = new Island(); // создан объект острова

        System.out.println("Настройки симуляции: \n 1 - автоматические \n 2 - пользовательские");
        switch (scanner.nextInt()) {
            case 1: {
                island.addLocationOnIsland(island.WIDTH, island.HEIGHT); // заполнить локациями
            }
            case 2: {
                System.out.println("введите параметры острова: \n высота: ");
                int width = scanner.nextInt();
                System.out.println("ширина: ");
                int height = scanner.nextInt();
                island.addLocationOnIsland(width, height); // заполнить локациями
            }
        }
    }
}
