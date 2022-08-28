package ua.com.shestakova.Island;

import com.diogonunes.jcolor.Attribute;
import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.Island.animal.*;
import ua.com.shestakova.Island.settingIsland.Tools;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;
import static ua.com.shestakova.Island.settingIsland.Island.field;


public class Statistics {

    @Getter
    @Setter
    private static int countDiedCreatures;
    @Getter
    @Setter
    private static int countNewCreatures;

    public static Map<String, Integer> firstGlobalInfo;
    public static Map<String, Integer> lastGlobalInfo;

    public static Map<String, Integer> firstTypeInfo;
    public static Map<String, Integer> lastTypeInfo;

    public void printIsland(PrintStream out) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                ArrayList<Сreature> loc = field[i][j].location;
                if (loc.size() > 0) {
                    String icon = loc.get(Tools.getRandomNumber(loc.size())).getIcon();
                    out.print(colorize("[", Attribute.RED_TEXT()) + icon + colorize("]"
                            , Attribute.RED_TEXT()));
                } else {
                    out.print(colorize("[ ]", Attribute.RED_TEXT()));
                }
            }
            out.println();
        }
    }

    public Map<String, Integer> getGlobalInformation() {
        int countAllAnimal = 0;
        int countPredator = 0;
        int countHerbivore = 0;
        int countPlant = 0;
        int filledLocations = 0;

        Map<String, Integer> firstInfo = new HashMap<>();

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].location.size() > 0) filledLocations++;
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature сreature = field[i][j].location.get(k);
                    countAllAnimal++;
                    if (сreature.getClass().equals(Plant.class)) countPlant++;
                    if (Predator.class.isAssignableFrom(сreature.getClass())) countPredator++;
                    if (Herbivore.class.isAssignableFrom(сreature.getClass())) countHerbivore++;
                }
            }
        }
        firstInfo.put("filledLocations", filledLocations);
        firstInfo.put("countAllAnimal", countAllAnimal);
        firstInfo.put("countPlant", countPlant);
        firstInfo.put("countPredator", countPredator);
        firstInfo.put("countHerbivore", countHerbivore);

        return firstInfo;
    }

    public void printGlobalStatistics(PrintStream out, Map<String, Integer> info) {
        out.println("In total there are " + info.get("countAllAnimal") + " living creatures in the location\n\n" +
                "Predators:" + colorize("" + info.get("countPredator"), Attribute.CYAN_TEXT()) +
                "\nHerbivores:" + colorize("" + info.get("countHerbivore"), Attribute.CYAN_TEXT()) +
                "\nPlants:" + colorize("" + info.get("countPlant"), Attribute.CYAN_TEXT()));
        out.println("Total locations: " + info.get("filledLocations"));

    }

    public Map<String, Integer> getActualCountType() {

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature animal = field[i][j].location.get(k);

                    if (map.containsKey(animal.getName())) {
                        map.put(animal.getName(), map.get(animal.getName()) + 1);
                    } else {
                        map.put(animal.getName(), 1);
                    }
                }
            }
        }
        return map;
    }

    public void printTypeAnimal(Map<String, Integer> map) {
        System.out.println(colorize("Total animals by types: ", Attribute.BLUE_BACK(), Attribute.BLACK_TEXT()));
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry);
        }
    }


    public static void printMiniStatistics(PrintStream out) {
        out.println(colorize("From start of simulation " + countDiedCreatures + " creatures died"));
        out.println(colorize("From start of simulation " + countNewCreatures + " creatures born"));
    }

    public void countingAndPrintResult(PrintStream out) {
        if (Statistics.firstGlobalInfo.get("countAllAnimal") < Statistics.lastGlobalInfo.get("countAllAnimal")) {
            out.println(colorize("The island got bigger!", Attribute.RED_TEXT()));
        }
        if (Statistics.firstGlobalInfo.get("countPlant") < Statistics.lastGlobalInfo.get("countPlant")) {
            out.println(colorize("Lots of plants now.\nTheir number has increased by " +
                            (Statistics.lastGlobalInfo.get("countPlant") - Statistics.firstGlobalInfo.get("countPlant")),
                    Attribute.GREEN_TEXT()));
        }
        if (Statistics.firstGlobalInfo.get("countPredator") < Statistics.lastGlobalInfo.get("countPredator")) {
            out.println("Your island has become more" + colorize(" dangerous", Attribute.RED_TEXT()) + ", there are " +
                    (Statistics.lastGlobalInfo.get("countPredator") - Statistics.firstGlobalInfo.get("countPredator")
                            + " more predators!"));
        }
        if (Statistics.firstGlobalInfo.get("countHerbivore") < Statistics.lastGlobalInfo.get("countHerbivore")) {
            out.println("But, there is enough food for them too) Count of herbivores now: " +
                    Statistics.lastGlobalInfo.get("countHerbivore"));
        } else {
            out.println("And this is sad, because count of herbivores has decreased by " +
                    (Statistics.firstGlobalInfo.get("countHerbivore") - Statistics.lastGlobalInfo.get("countHerbivore")
                            + " your wolves will eat you. Soon)"));
        }
        out.println("In the beginning, it was the most: " +
                Statistics.firstTypeInfo.entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue))
                        .map(Map.Entry::getKey)
                        .orElse(null));

        out.println("In the ending, it was the most: " +
                Statistics.lastTypeInfo.entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue))
                        .map(Map.Entry::getKey)
                        .orElse(null));
    }
}
