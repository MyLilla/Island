package ua.com.shestakova.Island.performingActions;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Сreature;
import ua.com.shestakova.Island.settingIsland.Location;
import ua.com.shestakova.Island.settingIsland.Tools;


import static java.lang.System.out;
import static ua.com.shestakova.Island.settingIsland.Island.field;

public class LifeTime {

    public int days = 0;

    public void callActionMove() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature сreature = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(сreature.getClass())) {
                        Animal runner = (Animal) сreature;
                        if (!runner.isMoved() && k >= 0 && runner.move(i, j)) {
                            k--;
                        }
                    }
                }
            }
        }
        days++;
        out.println("движение " + days);
    }

    public void finalizeTact() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature сreature = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(сreature.getClass())) {
                        Animal runner = (Animal) сreature;
                        runner.setSatiety(runner.getSatiety() - runner.getLossSatiety());
                        runner.setMoved(false);
                    }
                    сreature.utilize(i, j);
                }
            }
        }
        out.println("сброс флагов");
    }

    public void callActionEat() {

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
        days++;
        out.println("еда " + days);
    }

    public void callActionCopy() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Сreature сreature = field[i][j].location.get(k);

                    int countTypeInLoc = Location.getCountTypeInLoc(field[i][j].location, сreature);
                    if (сreature.getChanceMakeCopy() < Tools.getRandomNumber(Tools.MAX_PERCENT_BORD) &&
                            countTypeInLoc < сreature.getMaxCountTypeInLoc()) {
                        сreature.copy(field[i][j].location, countTypeInLoc);
                    }
                }
            }
        }
        days++;
        out.println("размножение " + days);
    }
}
