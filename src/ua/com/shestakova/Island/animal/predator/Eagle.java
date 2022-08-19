package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;

public class Eagle extends Predator{

    public Eagle() {

        setIcon("\uD83E\uDD85");
        setWeight(6);
        setMaxCountTypeInLoc(20);
        setSpeed(3);
        setCountFoodMax(1);
        setLossSatiety(0.3);
        getPercent().put("Fox", 10);
        getPercent().put("Rabbit", 90);
    }

    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass()) ||
                animal.getClass().equals(Fox.class);
    }
}
