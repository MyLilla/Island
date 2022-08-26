package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Herbivore;
import ua.com.shestakova.Island.animal.Predator;
import ua.com.shestakova.Island.animal.Сreature;

public class Eagle extends Predator {

    public Eagle() {

        setIcon("\uD83E\uDD85");
        setWeight(6);
        setMaxCountTypeInLoc(20);
        setSpeed(3);
        setCountFoodMax(1);
        setLossSatiety(0.3);
        getPercent().put("Fox", 10);
        getPercent().put("Rabbit", 90);
        getPercent().put("Boa", 80);
        getPercent().put("Deer", 0);
        getPercent().put("Horse", 0);
        getPercent().put("Mouse", 90);
        getPercent().put("Goat", 0);
        getPercent().put("Sheep", 0);
        getPercent().put("Boar", 0);
        getPercent().put("Buffalo", 0);
        getPercent().put("Duck", 80);
        getPercent().put("Caterpillar", 0);
    }

    @Override
    public boolean checkTypeAnimalForEat(Сreature animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass()) ||
                animal instanceof Fox;
    }
}
