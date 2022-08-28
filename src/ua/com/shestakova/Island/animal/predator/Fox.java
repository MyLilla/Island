package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Predator;

public class Fox extends Predator {

    public Fox() {
        setIcon("\uD83E\uDD8A");
        setWeight(8);
        setMaxCountTypeInLoc(30);
        setSpeed(2);
        setCountFoodMax(2);
        setLossSatiety(0.5);
        getPercent().put("Rabbit", 70);
        getPercent().put("Mouse", 90);
        getPercent().put("Boar", 5);
        getPercent().put("Duck", 60);
        getPercent().put("Caterpillar", 40);
    }
}
