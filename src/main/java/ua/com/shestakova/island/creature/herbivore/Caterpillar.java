package ua.com.shestakova.island.creature.herbivore;

import ua.com.shestakova.island.creature.Herbivore;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        setIcon("\uD83D\uDC1B");
        setWeight(0.01);
        setMaxCountTypeInLoc(1000);
        setLossSatiety(0.001);
    }
}
