package ua.com.shestakova.island.creature.herbivore;

import ua.com.shestakova.island.creature.Herbivore;

public class Goat extends Herbivore {

    public Goat() {
        setIcon("\uD83D\uDC10");
        setWeight(60);
        setMaxCountTypeInLoc(140);
        setSpeed(3);
        setCountFoodMax(10);
        setLossSatiety(2.5);
    }
}
