package ua.com.shestakova.Island;

import com.diogonunes.jcolor.Attribute;
import ua.com.shestakova.Island.settingIsland.Island;
import ua.com.shestakova.Island.settingIsland.Parser;
import ua.com.shestakova.Island.settingIsland.Tools;
import ua.com.shestakova.Island.performingActions.LifeTime;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.diogonunes.jcolor.Ansi.colorize;
import static ua.com.shestakova.Island.settingIsland.Tools.timeOfGame;

public class Dialog {
    public Statistics statistics = new Statistics();

    public void welcome(PrintStream out) {

        out.println(colorize("""
                Привет, тут симулируют жизни на острове.\s\s""", Attribute.YELLOW_TEXT()));
        out.println(colorize("""
                Ты хочешь запустить симуляцию с автонастройками?\s
                Тогда введи - 1\s""", Attribute.BLUE_TEXT()));
        out.println(colorize("""
                Ты хочешь поменять настройки симуляции?\s
                Тогда введи - 2""", Attribute.GREEN_TEXT()));

        int number = Tools.getNumberFromUser(1, 2);

        createIsland(out, number);

        out.println("Остров создан и заполнен существами, вот они:\n");
        statistics.printIsland(out);

        getMoreInformation(out);

        printRules(out);

        startSimulation(out);

        finish(out);

    }

    private void createIsland(PrintStream out, int number) {
        Island island = Island.getIsland();

        if (number == 2) {
            out.println(colorize("Нужны размеры острова.", Attribute.BLUE_TEXT()));
            out.println("введите высоту (число до 100): ");
            int width = Tools.getNumberFromUser(0, island.MAX_SIDE_OF_ISLAND);
            island.setWidth(width);
            out.println("введите высоту (число до 100): ");
            int height = Tools.getNumberFromUser(0, island.MAX_SIDE_OF_ISLAND);
            island.setHeight(height);
            out.println("введите максимальное число животных на локации: ");
            int maxCountInLocation = Tools.getNumberFromUser(0, Integer.MAX_VALUE);
            island.setMaxCountInLocation(maxCountInLocation);
            out.println("введите количество дней жизни локации: ");
            int timeOfGame = Tools.getNumberFromUser(0, Integer.MAX_VALUE);
            Tools.timeOfGame = timeOfGame;
        } else {
            Parser parser = new Parser();
            parser.getParametersFromProperties(island);
        }
        island.addLocationOnIsland(island.getWidth(), island.getHeight());
    }

    private void getMoreInformation(PrintStream out) {
        int number;
        out.println(colorize("""
                \nХотите узнать, сколько животных получилось?\s
                 1 - Нет\s
                 2 - Да""", Attribute.YELLOW_TEXT()));

        number = Tools.getNumberFromUser(1, 2);
        if (number == 2) {

            Statistics.firstInfo = (statistics.getGlobalInformation());
            statistics.printStatistics(out, Statistics.firstInfo);
            statistics.getAndPrintActualCountTypeAnimals();
        }
    }

    private void printRules(PrintStream out) {  // надо поправить
        out.println(colorize("Симуляция завершится, через " + timeOfGame +
                " суток", Attribute.TEXT_COLOR(5)));
    }

    private static void startSimulation(PrintStream out) {
        out.println("Для запуска симуляции введите любой текст и нажмите ENTER");
        Scanner scanner = new Scanner(System.in);
        scanner.next();

        LocalTime start = LocalTime.now();
        System.out.println(start.getSecond() + "время начала симуляции");

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < timeOfGame; i++) {
            executorService.submit(new LifeTime());
        }
        executorService.shutdown();
        try {
            if (executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("все потоки завершились");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LocalTime finish = LocalTime.now();
        System.out.println();
        System.out.println("на симуляцию ушло " + (finish.getSecond() - start.getSecond()) + " секунд");

    }

    private void finish(PrintStream out) {
        out.println(colorize("""
                Симуляция завершена!\s\s""", Attribute.YELLOW_TEXT()));
        out.println(colorize("""
                Хотите узнать, что стало с островом?\s
                Тогда введи - 1\s""", Attribute.BLUE_TEXT()));
        out.println(colorize("""
                Тебе все равно, и ты хочешь закончить?\s
                Тогда введи - 2""", Attribute.GREEN_TEXT()));

        switch (Tools.getNumberFromUser(1, 2)) {
            case 1:
                Statistics.lastInfo = (statistics.getGlobalInformation());
                statistics.printStatistics(out, Statistics.lastInfo);
                statistics.getAndPrintActualCountTypeAnimals();
                statistics.countingAndPrintResult(out);
            case 2: {
                out.println("Если запустишь снова, результаты могут быть другие! \uD83D\uDE0F");
            }
        }
    }
}
