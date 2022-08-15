package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Animal;

import static ua.com.shestakova.Island.settings.Island.field;

public class Deer extends Herbivore{

    public Deer() {

        setWeight(50);
        setCountFoodMax(8);
        setSpeed(4);
        setMaxCountInOneField(1);
        setIcon("\uD83E\uDD8C");
    }

    @Override
    public <T> void eat(T food) {

    }

    @Override
    public <T extends Animal> void copy(T couple) {

    }

    @Override
    public void die() {

    }


}
