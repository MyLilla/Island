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

        int choice = Tools.getNumberFromUser(1, 2);

        createIsland(out, choice);

        out.println("Остров создан и заполнен существами, вот они:\n");
        statistics.printIsland(out);

        getFirstInfo(out);

        printRules(out);

        startSimulation(out);

        finish(out);

    }

    private void createIsland(PrintStream out, int choice) {
        Island island = Island.getIsland();

        if (choice == 2) {
            out.println(colorize("Нужны размеры острова.", Attribute.BLUE_TEXT()));

            out.println("введите высоту (число до 100): ");
            island.setWidth(Tools.getNumberFromUser(0, island.MAX_SIDE_OF_ISLAND));
            out.println("введите высоту (число до 100): ");
            island.setHeight(Tools.getNumberFromUser(0, island.MAX_SIDE_OF_ISLAND));

            out.println("введите максимальное число животных на локации: ");
            island.setMaxCountInLocation(Tools.getNumberFromUser(0, Integer.MAX_VALUE));
            out.println("введите количество дней жизни локации: ");
            Tools.timeOfGame = Tools.getNumberFromUser(0, Integer.MAX_VALUE);

        } else {
            Parser parser = new Parser();
            parser.getParametersFromProperties(island);
        }
        island.addLocationOnIsland(island.getWidth(), island.getHeight());
    }

    private void getFirstInfo(PrintStream out) {

        Statistics.firstInfo = (statistics.getGlobalInformation());

        out.println(colorize("""
                \nХотите узнать, сколько животных получилось?\s
                 Нет - введите 1\s
                 Да - введите 2""", Attribute.YELLOW_TEXT()));

        int choice = Tools.getNumberFromUser(1, 2);
        if (choice == 2) {
            statistics.printStatistics(out, Statistics.firstInfo);
            statistics.getAndPrintActualCountTypeAnimals();
        }
    }

    private void printRules(PrintStream out) {
        out.println(colorize("Симуляция завершится, через " + timeOfGame +
                " суток", Attribute.TEXT_COLOR(5)));
    }

    private static void startSimulation(PrintStream out) {

        out.println("Для запуска симуляции введите любой текст и нажмите ENTER");
        Scanner scanner = new Scanner(System.in);
        scanner.next();

        LocalTime start = LocalTime.now();
        System.out.println(start + " время начала симуляции");

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        LifeTime lifeTime = new LifeTime();
        for (int i = 0; i < timeOfGame; i++) {
            executorService.submit(() -> {
                lifeTime.callActionCopy();
            });
            executorService.submit(lifeTime::callActionEat);

            executorService.submit(() -> {
                lifeTime.callActionMove();
            });
            executorService.submit(() -> {
                lifeTime.finalizeTact();
            });
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
