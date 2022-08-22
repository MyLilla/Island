package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Herbivore;
import ua.com.shestakova.Island.animal.Plant;

public class Duck extends Herbivore {

    public Duck() {
        setIcon("\uD83E\uDD86");
        setWeight(1);
        setMaxCountTypeInLoc(200);
        setSpeed(4);
        setCountFoodMax(0.15);
        setLossSatiety(0.4);
        getPercent().put("Caterpillar", 90);
    }

    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return animal instanceof Plant ||
                animal instanceof Caterpillar;
    }
}
