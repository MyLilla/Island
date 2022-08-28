package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Herbivore;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        setIcon("\uD83D\uDC1B");
        setWeight(0.01);
        setMaxCountTypeInLoc(1000);
        //setSpeed(0);
       // setCountFoodMax(1);
        setLossSatiety(0.001);
    }
}
