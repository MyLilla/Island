package ua.com.shestakova.Island.animal;

import java.util.ArrayList;

public class Plant extends Animal {
    public Plant() {
        setIcon("\uD83C\uDF31");
        setMaxCountInOneField(15);
        setWeight(3);
        setWeight(10);
    }


    @Override
    public int eat(ArrayList<Animal> animals) {
        return -1;
    }

    @Override
    public <T extends Animal> void copy(T couple) {

    }

    @Override
    public void die() {

    }
}
