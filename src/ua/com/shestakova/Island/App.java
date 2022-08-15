package ua.com.shestakova.Island;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.settings.Island;

import static ua.com.shestakova.Island.settings.Island.field;

public class App {
    public static void main(String[] args) {
        Island island = new Island(); // создан объект острова
        island.addLocationOnIsland(); // заполнить локациями

        print();
        System.out.println("_____________________________________");
        callAction();
        print();
        resetFlags();

        System.out.println("_____________________________________");
        callAction();
        print();

          }

    private static void callAction() {   // вызов действия

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) { // лист
                    Animal ani = field[i][j].location.get(k);  // животное
                    if (!ani.isMoved() && ani.getClass() != Plant.class) {
                        System.out.print("перемещение " + ani.getIcon() + " из " + i + j );
                        ani.move(i, j);
                        k = k-1;
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
                    field[i][j].location.get(k).setMoved(false);  // животное
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
}


