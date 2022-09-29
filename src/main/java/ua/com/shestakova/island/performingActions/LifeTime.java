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
                    Creature creature = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(creature.getClass())) {
                        Animal runner = (Animal) creature;
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
                    Creature creature = field[i][j].location.get(k);
                    determineType(creature);
                    creature.utilize(i, j);
                }
            }
        }
    }

    private void determineType(Creature creature) {
        if (Animal.class.isAssignableFrom(creature.getClass())) {
            Animal animal = (Animal) creature;
            animal.setSatiety(animal.getSatiety() - animal.getLossSatiety());
            animal.setMoved(false);
        } else {
            Plant plant = (Plant) creature;
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
                    Creature creature = field[i][j].location.get(k);
                    if (Animal.class.isAssignableFrom(creature.getClass())) {
                        Animal hunter = (Animal) creature;
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
                    Creature creature = field[i][j].location.get(k);

                    int countTypeInLoc = Location.getCountTypeInLoc(field[i][j].location, creature);
                    if (creature.getChanceMakeCopy() < Tools.getRandomNumber(Tools.MAX_PERCENT_BORD) &&
                            countTypeInLoc < creature.getMaxCountTypeInLoc()) {
                        creature.copy(field[i][j].location, countTypeInLoc);
                    }
                }
            }
        }
    }
}
