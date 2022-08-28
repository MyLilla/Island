package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Herbivore;
import ua.com.shestakova.Island.animal.Predator;
import ua.com.shestakova.Island.animal.Сreature;

public class Boa extends Predator {

    public Boa() {
        setIcon("\uD83D\uDC0D");
        setWeight(15);
        setMaxCountTypeInLoc(30);
        setSpeed(1);
        setCountFoodMax(3);
        setLossSatiety(1);
        getPercent().put("Fox", 15);
        getPercent().put("Rabbit", 20);
        getPercent().put("Mouse", 40);
        getPercent().put("Boar", 5);
        getPercent().put("Duck", 10);
    }

    @Override
    public boolean checkTypeAnimalForEat(Сreature animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass()) ||
                animal instanceof Fox;
    }
}

