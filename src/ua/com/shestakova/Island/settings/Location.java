package ua.com.shestakova.Island.settings;


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
    private HashMap<Integer, Class> mapAllAnimals = addedOfAllAnimals();

    public HashMap<Integer, Class> addedOfAllAnimals () {
        this.mapAllAnimals = new HashMap<>(); // мапа всех животных
        this.mapAllAnimals.put(0, Wolf.class);
        this.mapAllAnimals.put(1, Deer.class);
        this.mapAllAnimals.put(2, Plant.class);
        return mapAllAnimals;
    }

    public void createRandomLocation() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException { // создание локации с рандомным числом разных животных

        location = new ArrayList<>();  // локация
        int countLifesOnLocation = new Random().nextInt(5);

        for (int i = 1; i < countLifesOnLocation; i++) {
            int numberOfAnimal = new Random().nextInt(mapAllAnimals.size());  // рандомный выбор животного по ключу

            Class clazz = mapAllAnimals.get(numberOfAnimal);
            Constructor<?> constructor = clazz.getConstructor();

            location.add((Animal) constructor.newInstance());
        }

    }
    public ArrayList<Animal> createWithParameter(int countAnimalsOnFields) {
        return null;
    }
    public void checkNextStep(){
       /* если голодно - есть
         если сыт - двигаться
        если есть пара и сыт и ЖЦ ок - размножаться
        иначе - двигаться
        */
    }
}
