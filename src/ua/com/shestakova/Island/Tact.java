package ua.com.shestakova.Island;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.com.shestakova.Island.settings.Island.field;

public class Tact {

    public static void makeTact() {
        for (int i = 0; i < 5; i++) {
            print();
            System.out.println("_____________________________________");
            callActionCopy();
            print();
            System.out.println("_____________________________________");
            callActionEat();
            print();
            System.out.println("_____________________________________");
            callActionMove();
            print();
            System.out.println("_____________________________________");

            resetFlags();
            printInformationAfterTact();
        }


    }

    private static void callActionMove() {   // вызов действия

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) { // лист
                    Animal ani = field[i][j].location.get(k);  // животное
                    if (!ani.isMoved() && ani.getClass() != Plant.class && k >= 0) {
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
                    anim.setSatiety(anim.getSatiety() - anim.getLossEnergy());
                    anim.setMoved(false);  // животное
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

    private static void print() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("[" + i + j + "]"); // ячейка
                for (int k = 0; k < field[i][j].location.size(); k++) { // лист
                    System.out.print("{" + field[i][j].location.get(k).getIcon() + "}");  // животное
                }
            }
            System.out.println();
        }
    }

    public static void printInformationAfterTact() { // сохранение в джейсоне сколько животных на конец такта

        JsonParse parse = new JsonParse();
        HashMap<String, Animal> mapAnimalNow = new HashMap<>();


        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal anim = field[i][j].location.get(k);
                    mapAnimalNow.put(anim.getClass().getSimpleName(), anim);
                }
                System.out.println("на локации: " + i + j + " " + mapAnimalNow.size() + " вида животных");
            }
        }
//        System.out.println(mapAnimalNow);
//        parse.parserToJsonMAP(mapAnimalNow);
    }

}