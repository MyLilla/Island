package ua.com.shestakova.island;

import com.diogonunes.jcolor.Attribute;
import ua.com.shestakova.island.constructorGame.Tools;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;
import static ua.com.shestakova.island.constructorGame.Tools.*;
import static ua.com.shestakova.island.performingActions.Simulation.timeOfGame;

public class Dialog {

    public static final Statistics statistics = new Statistics();

    public boolean getTypeOfSettings(PrintStream out) {

        out.println(colorize("""
                Hello) It's a life simulation on the island.\s\s""", Attribute.YELLOW_TEXT()));
        out.println(colorize("""
                Do you want to start process with auto-settings?\s
                Please, enter number - 1\s""", Attribute.BLUE_TEXT()));
        out.println(colorize("""
                Do you want to change settings of simulation?\s
                So, enter number - 2\s""", Attribute.GREEN_TEXT()));

        return Tools.getNumberFromUser(CHOICE_ONE, CHOICE_TWO) == 1;
    }

    public Map<String, Integer> getParametersForCreateIsland(PrintStream out, boolean autoSettings) {

        if (autoSettings) {
            return settings;
        } else {
            out.println(colorize("I need a size of the island", Attribute.BLUE_TEXT()));

            Map<String, Integer> settings = new HashMap<>();

            out.println("Input WIDTH (number from 10 to 100): ");
            settings.put("WIDTH", Tools.getNumberFromUser(0, Tools.MAX_SIDE_OF_ISLAND));

            out.println("Input HEIGHT (number from 10 to 100): ");
            settings.put("HEIGHT", Tools.getNumberFromUser(0, Tools.MAX_SIDE_OF_ISLAND));

            out.println("Input MAX count characters in ONE location: ");
            settings.put("MAX_COUNT_IN_LOCATION", Tools.getNumberFromUser(0, Integer.MAX_VALUE));

            out.println("Input count life-days this island: ");
            settings.put("TIME_OF_GAME", Tools.getNumberFromUser(0, Integer.MAX_VALUE));

            return settings;
        }
    }

    public void printInitialisationInfo(PrintStream out) {

        out.println("Your island was created! Look:\n");
        statistics.printIsland(out);

        getInitialisationInfo(out);
        printRules(out);
    }

    private void getInitialisationInfo(PrintStream out) {

        statistics.setFirstGlobalInfo(statistics.getGlobalInformation());
        statistics.setFirstTypeInfo(statistics.getActualCountType());

        out.println(colorize("""
                \nDo you want to know how many creatures are there?\s
                 YES - input 1\s
                 NO - input 2""", Attribute.YELLOW_TEXT()));

        int choice = Tools.getNumberFromUser(CHOICE_ONE, CHOICE_TWO);
        if (choice == CHOICE_ONE) {
            statistics.printGlobalStatistics(out, statistics.getFirstGlobalInfo());
            statistics.printTypeAnimal(statistics.getFirstTypeInfo(), out);
        }
    }

    private void printRules(PrintStream out) {
        out.println(colorize("Simulation will be finished, in " + timeOfGame +
                " days", Attribute.TEXT_COLOR(5)));
        out.println(colorize("You'll see statistics information every day", Attribute.TEXT_COLOR(5)));
    }


    public void finish(PrintStream out) {
        out.println(colorize("""
                Simulation was finished!\s\s""", Attribute.YELLOW_TEXT()));

        statistics.setLastTypeInfo(statistics.getActualCountType());
        out.println(colorize("""
                Do you want to know what happened to the animals?\s
                input number 1\s""", Attribute.BLUE_TEXT()));
        out.println(colorize("""
                You don't care and you want to get out?\s
                input number 2""", Attribute.GREEN_TEXT()));

        switch (Tools.getNumberFromUser(CHOICE_ONE, CHOICE_TWO)) {
            case CHOICE_ONE:
                statistics.setLastGlobalInfo(statistics.getGlobalInformation());
                statistics.printGlobalStatistics(out, statistics.getLastGlobalInfo());
                statistics.printTypeAnimal(statistics.getLastTypeInfo(), out);
                statistics.countingAndPrintResult(out);
            case CHOICE_TWO: {
                out.println(colorize("If you run it again, the results may be different! \uD83D\uDE0F",
                        Attribute.RED_TEXT()));
            }
        }
    }
}
