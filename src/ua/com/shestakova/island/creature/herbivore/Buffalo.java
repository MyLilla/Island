package ua.com.shestakova.island.creature.herbivore;

import ua.com.shestakova.island.creature.Herbivore;

public class Buffalo extends Herbivore {

    public Buffalo() {
        setIcon("\uD83D\uDC03");
        setWeight(700);
        setMaxCountTypeInLoc(10);
        setSpeed(3);
        setCountFoodMax(100);
        setLossSatiety(2);
    }
}
