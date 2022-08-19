package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;

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
        getPercent().put("Deer", 0);
    }

    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass()) ||
                animal.getClass().equals(Fox.class);
    }
}

