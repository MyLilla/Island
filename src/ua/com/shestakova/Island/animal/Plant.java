package ua.com.shestakova.Island.animal;

import java.util.ArrayList;

public class Plant extends Animal {
    public Plant() {
        setIcon("\uD83C\uDF31");
        setMAX_COUNT_OF_THIS_ANIMAL(15);
        setWeight(3);
        setWeight(10);
    }


    @Override
    public void eat(ArrayList<Animal> animals) {
    }

    @Override
    public <T extends Animal> void copy(T couple) {

    }

    @Override
    public void die() {

    }
}
