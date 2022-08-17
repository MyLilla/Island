package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Animal;

import java.util.ArrayList;

import static ua.com.shestakova.Island.settings.Island.field;

public class Deer extends Herbivore{

    public Deer() {

        setWeight(40);
        setCountFoodMax(8);
        setSpeed(2);
        setMaxCountInOneField(2);
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
