package ua.com.shestakova.Island.settings;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.herbivore.Deer;
import ua.com.shestakova.Island.animal.herbivore.Rabbit;
import ua.com.shestakova.Island.animal.predator.Fox;
import ua.com.shestakova.Island.animal.predator.Wolf;

import java.util.HashMap;
import java.util.Random;

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

    public static int getRandomNumber (int board) {
        return new Random().nextInt(board);
    }
}
