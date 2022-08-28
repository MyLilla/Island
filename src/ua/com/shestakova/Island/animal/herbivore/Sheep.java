package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Herbivore;

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
