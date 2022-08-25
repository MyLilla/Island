package ua.com.shestakova.Island.performingActions;

import ua.com.shestakova.Island.Statistics;
import ua.com.shestakova.Island.animal.Animal;

import static java.lang.System.out;
import static ua.com.shestakova.Island.settingIsland.Island.field;

public class LifeTime implements Runnable {

    @Override
    public void run() {
        makeTact();
    }

    public static synchronized void makeTact() {

        // out.println("сутки начинаются");
        callActionCopy();
        callActionEat();
        callActionMove();
        finalizeTact();
        //  out.println("сутки закончились, вот результат: ");
        Statistics.printMiniStatistics(out);

    }

    private static synchronized void callActionMove() {

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

    public static synchronized void finalizeTact() {
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

    private static synchronized void callActionEat() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Animal ani = field[i][j].location.get(k);
                    ani.eat(field[i][j].location);
                }
            }
        }
    }

    private static synchronized void callActionCopy() {

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
