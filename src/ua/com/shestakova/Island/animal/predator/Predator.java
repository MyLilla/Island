package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;

import static ua.com.shestakova.Island.settings.Island.field;

public abstract class Predator extends Animal {
    @Override
    public <T> void eat(T food) {
        if (food.getClass() == Plant.class) {
            System.out.println("Predators doesn't eat Plants");
        }
    }

}
