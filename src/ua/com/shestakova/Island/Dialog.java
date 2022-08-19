package ua.com.shestakova.Island;

import ua.com.shestakova.Island.settings.Island;
import ua.com.shestakova.Island.settings.Parser;

import java.util.Scanner;

public class Dialog {
    public static void createIsland() {

        Island island = new Island(); // создан объект острова
        Scanner scanner = new Scanner(System.in);

        System.out.println("Менять настройки размеров симуляции? \n 1 - нет, оставить автоматические \n " +
                "2 - да, создать остров с измененными настройками");

            switch (scanner.nextInt()) {
                case 1: {
                   break;
                }
                case 2: {
                    Parser parser = new Parser();
                    parser.getParametersFromProperties(island);  // обновляем значения размеров
                } default:
                    System.out.println("Вы ввели не корректное число, остров будет создан с автоматическими настройками");  // выходит
            }

        island.addLocationOnIsland(island.getWIDTH(), island.getHEIGHT());
        System.out.println("Остров создан, вот он");
        Tact.print();
    }
}
