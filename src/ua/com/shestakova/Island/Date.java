package ua.com.shestakova.Island;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.herbivore.Deer;
import ua.com.shestakova.Island.animal.herbivore.Rabbit;
import ua.com.shestakova.Island.animal.predator.Fox;
import ua.com.shestakova.Island.animal.predator.Wolf;

import java.util.HashMap;

public class Date {

    public static HashMap<Integer, Animal> mapAllAnimals = addedOfAllAnimals();
    public static HashMap<Integer, Animal> addedOfAllAnimals() {
        mapAllAnimals = new HashMap<>();
        mapAllAnimals.put(0, new Wolf());
        mapAllAnimals.put(1, new Deer());
        mapAllAnimals.put(2, new Plant());
        mapAllAnimals.put(3, new Rabbit());
        mapAllAnimals.put(4, new Fox());
        return mapAllAnimals;
    }
}
