package ua.com.shestakova.Island.performingActions;

import ua.com.shestakova.Island.Statistics;
import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Сreature;
import ua.com.shestakova.Island.settingIsland.Location;
import ua.com.shestakova.Island.settingIsland.Tools;

import static java.lang.System.out;
import static ua.com.shestakova.Island.settingIsland.Island.field;

public class LifeTime {


//    public synchronized void makeTact() {
//
//        // out.println("сутки начинаются");
//        callActionCopy();
//        callActionEat();
//        callActionMove();
//        finalizeTact();
//        out.println("сутки закончились, вот результат: ");
//        Statistics.printMiniStatistics(out);
//
//    }

    public synchronized void callActionMove() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature ani = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(ani.getClass())) {
                        Animal runner = (Animal) ani;
                        if (!runner.isMoved() && k >= 0 && runner.move(i, j)) {
                            k--;
                        }
                    }
                }
            }
        }
    }

    public synchronized void finalizeTact() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature ani = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(ani.getClass())) {
                        Animal runner = (Animal) ani;
                        runner.setSatiety(runner.getSatiety() - runner.getLossSatiety());
                        runner.setMoved(false);
                        runner.utilize(i, j);
                    }
                }
            }
        }
    }

    public synchronized void callActionEat() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature сreature = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(сreature.getClass())) {
                        Animal hunter = (Animal) сreature;
                        hunter.eat(field[i][j].location);
                    }
                }
            }
        }
    }

    public static synchronized void callActionCopy() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature сreature = field[i][j].location.get(k);

                    int countTypeInLoc = Location.getCountTypeInLoc(field[i][j].location, сreature);
                    if (сreature.getChanceMakeCopy() > Tools.getRandomNumber(Tools.MAX_PERCENT_BORD) &&
                            countTypeInLoc < сreature.getMaxCountTypeInLoc()) {
                        сreature.copy(field[i][j].location, countTypeInLoc);
                    }
                }
            }
        }
    }
}
