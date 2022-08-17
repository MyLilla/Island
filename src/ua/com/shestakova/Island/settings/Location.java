package ua.com.shestakova.Island.settings;

import ua.com.shestakova.Island.Date;
import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.*;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.predator.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Location {

    public ArrayList<Animal> location;

    public void createRandomLocation() throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException { // создание локации с рандомным числом разных животных

        location = new ArrayList<>();
        int countLifesOnLocation = new Random().nextInt(5);

        for (int i = 1; i <= countLifesOnLocation; i++) {
            int numberOfAnimal = new Random().nextInt(Date.mapAllAnimals.size());  // рандомный выбор животного по ключу

            Animal randomAnimal = createNewAnimal(numberOfAnimal);

            if (location.isEmpty()) {
                location.add(randomAnimal);
            } else {
                int countTypeInLocation = 0;
                for (Animal animal : location) {
                    if (animal.getClass().equals(randomAnimal.getClass())) {
                        countTypeInLocation++;
                    }
                }
                if (countTypeInLocation < randomAnimal.getMaxCountInOneField()) {
                    location.add(randomAnimal);
                }
            }
        }
    }

    private Animal createNewAnimal(int animalNumber) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        Animal animal = Date.mapAllAnimals.get(animalNumber);
        Class clazz = animal.getClass();
        Constructor<?> constructor = clazz.getConstructor();
        return (Animal) constructor.newInstance();

    }

    public ArrayList<Animal> createWithParameter(int countAnimalsOnFields) {
        return null;
    }

}
