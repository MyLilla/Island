package ua.com.shestakova.Island;

import com.diogonunes.jcolor.Attribute;
import ua.com.shestakova.Island.settings.Island;
import ua.com.shestakova.Island.settings.Parser;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static ua.com.shestakova.Island.LifeTime.makeTact;

public class Dialog {
    public static void start() {

        Island island = Island.getIsland(); // создан объект острова
        Scanner scanner = new Scanner(System.in);

        System.out.println(colorize("""
                Менять настройки размеров симуляции?\s
                 1 - нет, оставить автоматические\s
                 2 - да, создать остров с измененными настройками""", Attribute.YELLOW_TEXT()));

        String number = scanner.nextLine();
            switch (Integer.parseInt(number)) {
                case 1: {
                   break;
                }
                case 2: {
                    Parser parser = new Parser();
                    parser.getParametersFromProperties(island);  // обновляем значения размеров
                    break;
                } default:
                    System.out.println("Вы ввели не корректное число, остров будет создан с автоматическими настройками");  // выходит
            }

        island.addLocationOnIsland(island.getWIDTH(), island.getHEIGHT());
        System.out.println("Остров создан, вот он");
        LifeTime.print();

        System.out.println("""
                Хотите узнать, сколько животных получилось?\s
                 1 - Нет\s
                 2 - Да""");


        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> {
            }
            case 2 -> {
                LifeTime.printInformation();
            }
            default -> System.out.println("Такого варианта нет");
        }

        System.out.println("Для запуска симуляции введите START");
        String start = scanner.nextLine();
        // добавить проверку, что START

        // поток запускается счетчик даты
        // запуск такта
        makeTact();
    }
}
