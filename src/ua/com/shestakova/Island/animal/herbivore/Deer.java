package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Animal;

public class Deer extends Herbivore{

    public Deer() {

        setWeight(40);
        setCountFoodMax(8);
        setSpeed(2);
        setMAX_COUNT_OF_THIS_ANIMAL(2);
        setIcon("\uD83E\uDD8C");
        setSatiety(100);
        setLossEnergy(5);
    }

    @Override
    public <T extends Animal> void copy(T couple) {

    }

    @Override
    public void die() {

    }


}
