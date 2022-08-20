package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Herbivore;

public class Rabbit extends Herbivore {

    public Rabbit() {
        setIcon("\uD83D\uDC07");
        setWeight(2);
        setMaxCountTypeInLoc(150);
        setSpeed(2);
        setCountFoodMax(0.45); //
        setLossSatiety(0.10);
    }
}
