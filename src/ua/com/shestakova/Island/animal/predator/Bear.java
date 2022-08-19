package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;

public class Bear extends Predator {

    public Bear() {
        setIcon("\uD83D\uDC3B");
        setWeight(500);
        setMaxCountTypeInLoc(5);
        setSpeed(2);
        setCountFoodMax(80);
        setLossSatiety(10);
        getPercent().put("Boa", 80);
        getPercent().put("Rabbit", 80);
        getPercent().put("Deer", 80);
    }

    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass()) ||
                animal.getClass().equals(Boa.class);
    }
}
