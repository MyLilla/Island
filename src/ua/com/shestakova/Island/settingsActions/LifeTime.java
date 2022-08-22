package ua.com.shestakova.Island.settingsActions;

import ua.com.shestakova.Island.Statistics;
import ua.com.shestakova.Island.animal.Animal;

import static java.lang.System.out;
import static ua.com.shestakova.Island.settingIsland.Island.field;

public class LifeTime implements Runnable {

    @Override
    public void run() {
        // еще думаю над этим
    }

    public static void makeTact() {

        for (int i = 0; i < Time.timeOfGame; i++) {

            out.println("сутки " + i + " начинаются");  // вывожу только для себя, потом уберу
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
                    if (!ani.isMoved() && k >= 0 && ani.move(i, j)) {
                        k--;   // специально для Антона
                    }
                }
            }
        }
    }

    public static void resetFlags() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal ani = field[i][j].location.get(k);
                    ani.setSatiety(ani.getSatiety() - ani.getLossSatiety());
                    ani.setMoved(false);  //
                    ani.utilize(i, j);
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
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal ani = field[i][j].location.get(k);
                    ani.copy(field[i][j].location);
                }
            }
        }
    }
}
