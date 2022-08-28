package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Herbivore;
import ua.com.shestakova.Island.animal.Predator;
import ua.com.shestakova.Island.animal.Сreature;

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
    public boolean checkTypeAnimalForEat(Сreature animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass()) ||
                animal instanceof Boa;
    }
}
