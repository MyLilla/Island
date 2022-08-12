package ua.com.shestakova.Island.FieldOfIsland;


import ua.com.shestakova.Island.Animal.Animal;
import ua.com.shestakova.Island.Animal.Herbivore.*;
import ua.com.shestakova.Island.Animal.Plant;
import ua.com.shestakova.Island.Animal.Predator.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Location {

    public ArrayList<Animal> location;
    public  HashMap<Integer, Class> mapAllAnimals = addedOfAllAnimals();

    public HashMap<Integer, Class> addedOfAllAnimals () {
        this.mapAllAnimals = new HashMap<>(); // мапа всех животных
        this.mapAllAnimals.put(0, Wolf.class);
        this.mapAllAnimals.put(1, Deer.class);
        this.mapAllAnimals.put(2, Plant.class);
        return mapAllAnimals;
    }

    public void createRandomLocation() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException { // создание локации с рандомным числом разных животных

        location = new ArrayList<>();  // локация
        int countAnimalsOnFields = new Random().nextInt(10);     // количество животных в локации

        for (int i = 1; i < countAnimalsOnFields; i++) {  // создать нужное количество животных раз
            int numberOfAnimal = new Random().nextInt(mapAllAnimals.size());  // рандомный выбор животного по ключу

            Class clazz = mapAllAnimals.get(numberOfAnimal); // получен класс рандомного животного
            Constructor<?> constructor = clazz.getConstructor(); // и конструктор

            //System.out.println(Animal.class.isAssignableFrom(clazz))
            Animal newInstance = (Animal) constructor.newInstance(); // создан объект
            location.add(newInstance); // добавлен в локацию
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
