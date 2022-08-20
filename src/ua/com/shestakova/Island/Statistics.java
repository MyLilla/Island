package ua.com.shestakova.Island;

import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.Herbivore;
import ua.com.shestakova.Island.animal.Predator;
import ua.com.shestakova.Island.building.Tools;

import java.io.PrintStream;
import java.util.ArrayList;

import static com.diogonunes.jcolor.Ansi.colorize;
import static ua.com.shestakova.Island.building.Island.field;

@Getter
@Setter
public class Statistics {

    private static int countAllAnimal;
    private static int countPredator;
    private static int countHerbivore;
    private static int countPlant;
    private static int filledLocations;
    @Getter
    @Setter
    private static int countDiedAnimal;

    public static void printIsland(PrintStream out) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                ArrayList<Animal> loc = field[i][j].location;
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

    public static void getActualInformation() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].location.size() > 0) filledLocations++;

                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal anim = field[i][j].location.get(k);
                    countAllAnimal++;
                    if (anim.getClass().equals(Plant.class)) countPlant++;
                    if (Predator.class.isAssignableFrom(anim.getClass())) countPredator++;
                    if (Herbivore.class.isAssignableFrom(anim.getClass())) countHerbivore++;
                }
            }
        }
    }

    public static void printStatistics(PrintStream out) {
        out.println("Всего на локации " + countAllAnimal + " животных\n" +
                "Из них хищников: " + countPredator +
                "\nТравоядных: " + countHerbivore + "\nРастений: " + countPlant);
        out.println(colorize("Всего заполненных локаций: " + filledLocations));
    }
}
