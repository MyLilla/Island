package ua.com.shestakova.Island.animal;

import java.util.ArrayList;

public class Plant extends Animal {
    public Plant() {
        setIcon("\uD83C\uDF31");
        setWeight(1);
        setMaxCountTypeInLoc(200);
    }

    @Override
    public void eat(ArrayList<Animal> animals) {
    }

    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return false;
    }

    @Override
    public void copy(ArrayList<Animal> animals) {
    }
}
