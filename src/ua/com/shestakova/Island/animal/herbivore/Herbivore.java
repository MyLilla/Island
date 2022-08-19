package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;

public abstract class Herbivore extends Animal {

    public Herbivore() {
        getPercent().put("Plant", 50);
    }
    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return animal instanceof Plant;
    }
}