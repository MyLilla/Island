package ua.com.shestakova.island.creature.herbivore;

import ua.com.shestakova.island.creature.Herbivore;

public class Horse extends Herbivore {

    public Horse() {
        setIcon("\uD83D\uDC0E");
        setWeight(400);
        setMaxCountTypeInLoc(20);
        setSpeed(4);
        setCountFoodMax(60);
        setLossSatiety(2);
    }
}
