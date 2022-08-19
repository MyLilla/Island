package ua.com.shestakova.Island;

import com.diogonunes.jcolor.Attribute;
import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;
import ua.com.shestakova.Island.animal.predator.Predator;

import static com.diogonunes.jcolor.Ansi.colorize;
import static ua.com.shestakova.Island.settings.Island.field;

public class LifeTime {


    public static void makeTact() {

        callActionCopy();
        System.out.println("Животные размножились");

        callActionEat();
        System.out.println("Животные поели");

        callActionMove();
        System.out.println("Животные переместились");

        resetFlags();
        print();

       printInformation();
    }

    private static void callActionMove() {   // вызов действия

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) { // лист
                    Animal ani = field[i][j].location.get(k);  // животное
                    if (!ani.isMoved() && k >= 0) {
                        // System.out.print("перемещение " + ani.getIcon() + " из " + i + j);
                        if (ani.move(i, j)) {
                            k = k - 1;
                        }
                    }
                }
            }
        }
    }

    // сброс флaгов действия
    public static void resetFlags() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) { // лист
                    Animal anim = field[i][j].location.get(k);
                    anim.setSatiety(anim.getSatiety() - anim.getLossSatiety());
                    anim.setMoved(false);  //
                    anim.die(i, j);
                }
            }
        }
    }

    private static void callActionEat() {   // вызов действия

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) { // лист
                    Animal ani = field[i][j].location.get(k);  // животное
                    ani.eat(field[i][j].location);

                }
            }
        }
    }

    private static void callActionCopy() {   // вызов действия

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                //  System.out.println("на локации " + i + j);
                for (int k = 0; k < field[i][j].location.size(); k++) { // лист
                    Animal ani = field[i][j].location.get(k);  // животное
                    ani.copy(field[i][j].location);
                }
            }
        }
    }

    public static void print() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("[" + i + j + "]"); // ячейка
                for (int k = 0; k < field[i][j].location.size(); k++) { // лист
                    System.out.print(colorize ("{" + field[i][j].location.get(k).getIcon() + "}", Attribute.BLUE_TEXT()));  // животное
                }
            }
            System.out.println();
        }
    }

    public static void printInformation() {

        int countAllAnimal = 0;
        int predator = 0;
        int herbivore = 0;
        int plant = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal anim = field[i][j].location.get(k);
                    countAllAnimal++;
                    if (anim.getClass().equals(Plant.class)) plant++;
                    if (Predator.class.isAssignableFrom(anim.getClass())) predator++;
                    if (Herbivore.class.isAssignableFrom(anim.getClass())) herbivore++;
                    //  mapAnimalNow.put(anim.getClass().getSimpleName(), anim);
                }
                //  System.out.println("на локации: " + i + j + " " + mapAnimalNow.size() + " вида животных");
            }
        }
        System.out.println("Всего на локации " + countAllAnimal + " животных");
        System.out.println("Из них хищников: " + predator);
        System.out.println("Травоядных: " + herbivore);
        System.out.println("Растений: " + plant);

    }
}