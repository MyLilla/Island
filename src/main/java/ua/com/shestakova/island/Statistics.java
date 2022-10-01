package ua.com.shestakova.island;

import com.diogonunes.jcolor.Attribute;
import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.island.constructorGame.Tools;
import ua.com.shestakova.island.creature.Creature;
import ua.com.shestakova.island.creature.Herbivore;
import ua.com.shestakova.island.creature.Plant;
import ua.com.shestakova.island.creature.Predator;

import java.io.PrintStream;
import java.util.*;

import static com.diogonunes.jcolor.Ansi.colorize;
import static ua.com.shestakova.island.constructorGame.Island.field;

@Getter
@Setter
public class Statistics {

    private Map<String, Integer> firstGlobalInfo;
    private Map<String, Integer> lastGlobalInfo;
    private Map<String, Integer> firstTypeInfo;
    private Map<String, Integer> lastTypeInfo;
    private int countDiedCreatures;
    private int countNewCreatures;

    public void printIsland(PrintStream out) {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                List<Creature> location = field[x][y].location;
                if (!location.isEmpty()) {
                    String icon = location.get(Tools.getRandomNumber(location.size())).getIcon();
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


        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                if (!field[x][y].location.isEmpty()) filledLocations++;
                for (int k = 0; k < field[x][y].location.size(); k++) {
                    Creature creature = field[x][y].location.get(k);
                    countAllAnimal++;
                    if (creature.getClass().equals(Plant.class)) countPlant++;
                    if (Predator.class.isAssignableFrom(creature.getClass())) countPredator++;
                    if (Herbivore.class.isAssignableFrom(creature.getClass())) countHerbivore++;
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

        Map<String, Integer> typesMap = new HashMap<>();

        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                for (int countAnimal = 0; countAnimal < field[x][y].location.size(); countAnimal++) {
                    Creature animal = field[x][y].location.get(countAnimal);
                    typesMap.merge(animal.getName(), 1, Integer::sum);
                }
            }
        }
        return typesMap;
    }

    public void printTypeAnimal(Map<String, Integer> map, PrintStream out) {
        out.println(colorize("Total animals by types: ", Attribute.BLUE_BACK(), Attribute.BLACK_TEXT()));
        for (Map.Entry entry : map.entrySet()) {
            out.println(entry);
        }
    }

    public void printMiniStatistics(PrintStream out) {
        out.printf(colorize("From start of simulation %s creatures died\n"), countDiedCreatures);
        out.printf(colorize("From start of simulation %s creatures born\n"), countNewCreatures);
    }

    public void countingAndPrintResult(PrintStream out) {
        if (firstGlobalInfo.get("countAllAnimal") < lastGlobalInfo.get("countAllAnimal")) {
            out.println(colorize("The island got bigger!", Attribute.RED_TEXT()));
        }
        if (firstGlobalInfo.get("countPlant") < lastGlobalInfo.get("countPlant")) {
            out.println(colorize("Lots of plants now.\nTheir number has increased by " +
                            (lastGlobalInfo.get("countPlant") - firstGlobalInfo.get("countPlant")),
                    Attribute.GREEN_TEXT()));
        }
        if (firstGlobalInfo.get("countPredator") < lastGlobalInfo.get("countPredator")) {
            out.println("Your island has become more" + colorize(" dangerous", Attribute.RED_TEXT()) + ", there are " +
                    (lastGlobalInfo.get("countPredator") - firstGlobalInfo.get("countPredator")
                            + " more predators!"));
        }
        if (firstGlobalInfo.get("countHerbivore") < lastGlobalInfo.get("countHerbivore")) {
            out.println("But, there is enough food for them too) Count of herbivores now: " +
                    lastGlobalInfo.get("countHerbivore"));
        } else {
            out.println("And this is sad, because count of herbivores has decreased by " +
                    (firstGlobalInfo.get("countHerbivore") - lastGlobalInfo.get("countHerbivore")
                            + " your wolves will eat you. Soon)"));
        }
        out.println("In the beginning, it was the most: " +
                firstTypeInfo.entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue))
                        .map(Map.Entry::getKey)
                        .orElse(null));

        out.println("In the ending, it was the most: " +
                lastTypeInfo.entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue))
                        .map(Map.Entry::getKey)
                        .orElse(null));
    }
}
