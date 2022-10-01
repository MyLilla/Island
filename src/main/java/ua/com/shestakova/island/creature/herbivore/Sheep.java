package ua.com.shestakova.island.creature.herbivore;

import ua.com.shestakova.island.creature.Herbivore;

public class Sheep extends Herbivore {
    public Sheep() {
        setIcon("\uD83D\uDC11");
        setWeight(70);
        setMaxCountTypeInLoc(140);
        setSpeed(3);
        setCountFoodMax(15);
        setLossSatiety(3.5);
    }
}
