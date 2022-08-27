package ua.com.shestakova.Island;

import com.diogonunes.jcolor.Attribute;
import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.Island.animal.*;
import ua.com.shestakova.Island.settingIsland.Tools;

import java.io.PrintStream;
import java.util.ArrayList;
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

    public static Map<String, Integer> firstInfo;
    public static Map<String, Integer> lastInfo;

    public void printIsland(PrintStream out) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                ArrayList<Сreature> loc = field[i][j].location;
                if (loc.size() > 0) {
                    String icon = loc.get(Tools.getRandomNumber(loc.size())).getIcon();
                    out.print("[" + icon + "]");
                } else {
                    out.print("[ ]");
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

    public void getAndPrintActualCountTypeAnimals() {

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
        System.out.println("Всего животных по видам: ");
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry);
        }
    }


    public void printStatistics(PrintStream out, Map<String, Integer> info) {
        out.println("Всего на локации " + info.get("countAllAnimal") + " животных\n" +
                "Из них хищников: " + info.get("countPredator") +
                "\nТравоядных: " + info.get("countHerbivore") + "\nРастений: " + info.get("countPlant"));
        out.println(colorize("Всего заполненных локаций: " + info.get("filledLocations")));
        ;
    }

    public static void printMiniStatistics(PrintStream out) {
        out.println(colorize("С начала симуляции умерло: " + countDiedCreatures + " животных"));
        out.println(colorize("С начала симуляции родилось: " + countNewCreatures + " животных"));
    }

    public void countingAndPrintResult(PrintStream out) {
        if (Statistics.firstInfo.get("countAllAnimal") < Statistics.lastInfo.get("countAllAnimal")) {
            out.println("Остров стал заметно больше!");
        }
        if (Statistics.firstInfo.get("countPlant") < Statistics.lastInfo.get("countPlant")) {
            out.println("Растений теперь много!\nИх число увеличилось на " +
                    (Statistics.lastInfo.get("countPlant") - Statistics.firstInfo.get("countPlant")));
        }
        if (Statistics.firstInfo.get("countPredator") < Statistics.lastInfo.get("countPredator")) {
            out.println("Твой остров стал опаснее, хищников стало на " +
                    (Statistics.lastInfo.get("countPredator") - Statistics.firstInfo.get("countPredator") + " больше!"));
        }
        if (Statistics.firstInfo.get("countHerbivore") < Statistics.lastInfo.get("countHerbivore")) {
            out.println("Но ничего, еды для них тоже хватает. Травоядных теперь: " +
                    Statistics.lastInfo.get("countHerbivore"));
        } else {
            out.println("И это печально, ведь число травоядных уменьшилось на " +
                    (Statistics.firstInfo.get("countHerbivore") - Statistics.lastInfo.get("countHerbivore")
                            + " скоро твои волки сожрут тебя)"));
        }
    }
}
