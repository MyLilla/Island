package ua.com.shestakova.Island.settingsActions;

import ua.com.shestakova.Island.Statistics;
import ua.com.shestakova.Island.animal.Animal;

import static java.lang.System.out;
import static ua.com.shestakova.Island.building.Island.field;

public class LifeTime implements Runnable {

    @Override
    public void run() {
        // пройти циклом поле, и запустить действия в каждой локации
        // пройти по всем классам, и для каждого вызвать действие
    }

    public static void makeTact() {

        for (int i = 0; i < Time.TIME_OF_GAME; i++) {

            out.println("сутки " + i + " начинаются");
            callActionCopy();
            callActionEat();
            callActionMove();
            resetFlags();
            out.println("сутки " + i + " закончились, вот результат: ");
            Statistics.printIsland(out);
        }

    }

    private static void callActionMove() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal ani = field[i][j].location.get(k);
                    if (!ani.isMoved() && k >= 0) {
                        if (ani.move(i, j)) {
                            k = k - 1;
                        }
                    }
                }
            }
        }
    }

    public static void resetFlags() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal anim = field[i][j].location.get(k);
                    anim.setSatiety(anim.getSatiety() - anim.getLossSatiety());
                    anim.setMoved(false);  //
                    anim.die(i, j);
                }
            }
        }
    }

    private static void callActionEat() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal ani = field[i][j].location.get(k);
                    ani.eat(field[i][j].location);

                }
            }
        }
    }

    private static void callActionCopy() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                //  System.out.println("на локации " + i + j);
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal ani = field[i][j].location.get(k);
                    ani.copy(field[i][j].location);
                }
            }
        }
    }

}