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
        getPercent().put("Mouse", 90);
        getPercent().put("Duck", 80);
    }

    @Override
    public boolean checkTypeAnimalForEat(Сreature animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass()) ||
                animal instanceof Fox;
    }
}
