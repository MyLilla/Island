package ua.com.shestakova.island;

import com.diogonunes.jcolor.Attribute;
import ua.com.shestakova.island.exceptions.ThreadsException;
import ua.com.shestakova.island.constructorGame.Island;
import ua.com.shestakova.island.constructorGame.Parser;
import ua.com.shestakova.island.constructorGame.Tools;
import ua.com.shestakova.island.performingActions.LifeTime;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.diogonunes.jcolor.Ansi.colorize;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static ua.com.shestakova.island.constructorGame.Tools.daysOfGame;

public class Dialog {
    private final Statistics statistics = new Statistics();
    private final LifeTime lifeTime = new LifeTime();

    public void welcome(PrintStream out) {

        out.println(colorize("""
                Hello) It's a life simulation on the island.\s\s""", Attribute.YELLOW_TEXT()));
        out.println(colorize("""
                Do you want to start process with auto-settings?\s
                Please, enter number - 1\s""", Attribute.BLUE_TEXT()));
        out.println(colorize("""
                Do you want to change settings of simulation?\s
                So, enter number - 2\s""", Attribute.GREEN_TEXT()));

        int choice = Tools.getNumberFromUser(1, 2);
        createIsland(out, choice);

        out.println("Your island was created! Look:\n");
        statistics.printIsland(out);

        getFirstInfo(out);
        printRules(out);

        startSimulation(out);

        finish(out);
    }

    private void createIsland(PrintStream out, int choice) {
        Island island = Island.getIsland();

        if (choice == 2) {
            out.println(colorize("I need a size of the island", Attribute.BLUE_TEXT()));

            out.println("Input WIDTH (number from 10 to 100): ");
            island.setWidth(Tools.getNumberFromUser(0, island.MAX_SIDE_OF_ISLAND));
            out.println("Input HEIGHT (number from 10 to 100): ");
            island.setHeight(Tools.getNumberFromUser(0, island.MAX_SIDE_OF_ISLAND));

            out.println("Input MAX count characters in ONE location: ");
            island.setMaxCountInLocation(Tools.getNumberFromUser(0, Integer.MAX_VALUE));
            out.println("Input count life-days this island: ");
            Tools.daysOfGame = Tools.getNumberFromUser(0, Integer.MAX_VALUE);

        } else {
            Parser parser = new Parser();
            parser.getParametersFromProperties(island);
        }
        island.addLocationOnIsland(island.getWidth(), island.getHeight());
    }

    private void getFirstInfo(PrintStream out) {

        Statistics.firstGlobalInfo = statistics.getGlobalInformation();
        Statistics.firstTypeInfo = statistics.getActualCountType();

        out.println(colorize("""
                \nDo you want to know how many creatures are there?\s
                 NO - input 1\s
                 YES - input 2""", Attribute.YELLOW_TEXT()));

        int choice = Tools.getNumberFromUser(1, 2);
        if (choice == 2) {
            statistics.printGlobalStatistics(out, Statistics.firstGlobalInfo);
            statistics.printTypeAnimal(Statistics.firstTypeInfo);
        }
    }

    private void printRules(PrintStream out) {
        out.println(colorize("Simulation will be finished, in " + daysOfGame +
                " days", Attribute.TEXT_COLOR(5)));
        out.println(colorize("You'll see statistics information every day", Attribute.TEXT_COLOR(5)));
    }

    private void startSimulation(PrintStream out) {

        out.println("To run the simulation, enter any text and click ENTER");
        Scanner scanner = new Scanner(System.in);
        scanner.next();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < daysOfGame; i++) {
            out.println("It is day number" + colorize(" " + (i + 1), Attribute.BRIGHT_RED_TEXT()));
            startActionsThreads(executorService);
            lifeTime.finalizeTact();
            Statistics.printMiniStatistics(out);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new ThreadsException("Sleep Exception in days tact" + e);
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(daysOfGame, MILLISECONDS);
        } catch (InterruptedException e) {
            throw new ThreadsException("Thread was interrupted " + e);
        }
    }

    private void startActionsThreads(ExecutorService executorService) {
        ReentrantLock reentrantLock = new ReentrantLock();

        executorService.submit(() -> {
            reentrantLock.lock();
            lifeTime.callActionCopy();
            reentrantLock.unlock();
        });
        executorService.submit(() -> {
            reentrantLock.lock();
            lifeTime.callActionEat();
            reentrantLock.unlock();
        });
        executorService.submit(() -> {
            reentrantLock.lock();
            lifeTime.callActionMove();
            reentrantLock.unlock();
        });
    }

    private void finish(PrintStream out) {
        out.println(colorize("""
                Simulation was finished!\s\s""", Attribute.YELLOW_TEXT()));

        Statistics.lastTypeInfo = statistics.getActualCountType();
        out.println(colorize("""
                Do you want to know what happened to the animals?\s
                input number 1\s""", Attribute.BLUE_TEXT()));
        out.println(colorize("""
                You don't care and you want to get out?\s
                input number 2""", Attribute.GREEN_TEXT()));

        switch (Tools.getNumberFromUser(1, 2)) {
            case 1:
                Statistics.lastGlobalInfo = (statistics.getGlobalInformation());
                statistics.printGlobalStatistics(out, Statistics.lastGlobalInfo);
                statistics.printTypeAnimal(Statistics.lastTypeInfo);
                statistics.countingAndPrintResult(out);
            case 2: {
                out.println(colorize("If you run it again, the results may be different! \uD83D\uDE0F",
                        Attribute.RED_TEXT()));
            }
        }
    }
}
