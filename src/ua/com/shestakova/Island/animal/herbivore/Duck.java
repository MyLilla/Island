package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Herbivore;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.Сreature;

public class Duck extends Herbivore {

    public Duck() {
        setIcon("\uD83E\uDD86");
        setWeight(1);
        setMaxCountTypeInLoc(200);
        setSpeed(4);
        setCountFoodMax(0.15);
        setLossSatiety(0.2);
        getPercent().put("Caterpillar", 90);
    }

    @Override
    public boolean checkTypeAnimalForEat(Сreature сreature) {
        return сreature instanceof Plant ||
                сreature instanceof Caterpillar;
    }
}
