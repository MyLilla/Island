package ua.com.shestakova.island.performingActions;

import ua.com.shestakova.island.creature.Animal;
import ua.com.shestakova.island.creature.Creature;
import ua.com.shestakova.island.creature.Herbivore;
import ua.com.shestakova.island.creature.Plant;
import ua.com.shestakova.island.constructorGame.Location;
import ua.com.shestakova.island.constructorGame.Tools;

import static ua.com.shestakova.island.constructorGame.Island.field;

public class LifeTime {

    public void callActionMove() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Creature сreature = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(сreature.getClass())) {
                        Animal runner = (Animal) сreature;
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
                    Creature сreature = field[i][j].location.get(k);
                    determineType(сreature);
                    сreature.utilize(i, j);
                }
            }
        }
    }

    private void determineType(Creature сreature) {
        if (Animal.class.isAssignableFrom(сreature.getClass())) {
            Animal animal = (Animal) сreature;
            animal.setSatiety(animal.getSatiety() - animal.getLossSatiety());
            animal.setMoved(false);
        } else {
            Plant plant = (Plant) сreature;
            plant.setCountDaysLife(plant.getCountDaysLife() - 1);
            if (plant.getCountDaysLife() < 0) {
                plant.setAlive(false);
            }
        }
    }

    public void callActionEat() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Creature сreature = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(сreature.getClass())) {
                        Animal hunter = (Animal) сreature;
                        hunter.eat(field[i][j].location);
                        if (hunter instanceof Herbivore) {
                            hunter.eat(field[i][j].location);
                        }
                    }
                }
            }
        }
    }

    public void callActionCopy() {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                for (int k = 0; k < field[i][j].location.size(); k++) {
                    Creature сreature = field[i][j].location.get(k);

                    int countTypeInLoc = Location.getCountTypeInLoc(field[i][j].location, сreature);
                    if (сreature.getChanceMakeCopy() < Tools.getRandomNumber(Tools.MAX_PERCENT_BORD) &&
                            countTypeInLoc < сreature.getMaxCountTypeInLoc()) {
                        сreature.copy(field[i][j].location, countTypeInLoc);
                    }
                }
            }
        }
    }
}
