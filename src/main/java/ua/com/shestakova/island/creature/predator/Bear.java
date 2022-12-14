package ua.com.shestakova.island.creature.predator;

import ua.com.shestakova.island.creature.Creature;
import ua.com.shestakova.island.creature.Herbivore;
import ua.com.shestakova.island.creature.Predator;

public class Bear extends Predator {

    public Bear() {
        setIcon("\uD83D\uDC3B");
        setWeight(500);
        setMaxCountTypeInLoc(5);
        setSpeed(2);
        setCountFoodMax(80);
        setLossSatiety(20);
        getPercent().put("Boa", 80);
        getPercent().put("Rabbit", 90);
        getPercent().put("Deer", 80);
        getPercent().put("Horse", 40);
        getPercent().put("Mouse", 90);
        getPercent().put("Goat", 70);
        getPercent().put("Sheep", 70);
        getPercent().put("Boar", 50);
        getPercent().put("Buffalo", 20);
        getPercent().put("Duck", 10);
    }

    @Override
    public boolean checkTypeAnimalForEat(Creature animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass()) ||
                animal instanceof Boa;
    }
}
