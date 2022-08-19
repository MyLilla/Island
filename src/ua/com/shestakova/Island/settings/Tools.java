package ua.com.shestakova.Island.settings;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.herbivore.Deer;
import ua.com.shestakova.Island.animal.herbivore.Rabbit;
import ua.com.shestakova.Island.animal.predator.*;

import java.util.HashMap;
import java.util.Random;

public class Tools {

    public static HashMap<Integer, Animal> mapAllAnimals = addedOfAllAnimals();
    public static HashMap<Integer, Animal> addedOfAllAnimals() {
        mapAllAnimals = new HashMap<>();
        mapAllAnimals.put(0, new Wolf());
        mapAllAnimals.put(1, new Deer());
        mapAllAnimals.put(2, new Plant());
        mapAllAnimals.put(3, new Rabbit());
        mapAllAnimals.put(4, new Fox());
        mapAllAnimals.put(5, new Boa());
        mapAllAnimals.put(6, new Bear());
        mapAllAnimals.put(7, new Eagle());
        return mapAllAnimals;
    }

    public static int getRandomNumber (int board) {
        return new Random().nextInt(board);
    }

    public static void checkNumber (String number) {
        try {
            int result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            // ??
        }
    }
}
