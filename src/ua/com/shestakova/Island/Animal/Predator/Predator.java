package ua.com.shestakova.Island.Animal.Predator;


import ua.com.shestakova.Island.Animal.Animal;
import ua.com.shestakova.Island.Animal.Plant;

public abstract class Predator extends Animal {
    @Override
    public <T> void eat(T food) {
        if (food.getClass() == Plant.class) {
            System.out.println("Predators doesn't eat Plants");
        }
    }
}
