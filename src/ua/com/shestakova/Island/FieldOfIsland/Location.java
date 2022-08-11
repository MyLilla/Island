package ua.com.shestakova.Island.FieldOfIsland;


import ua.com.shestakova.Island.Animal.Animal;
import ua.com.shestakova.Island.Animal.Herbivore.*;
import ua.com.shestakova.Island.Animal.Predator.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Location {

    public ArrayList<Animal> location;


    public void doOneAction(ArrayList<Animal> location){
        // перебрать всех обитателей и сделать чек действия

    }
    public void checkNextStep(){
       /* если голодно - есть
         если сыт - двигаться
        если есть пара и сыт и ЖЦ ок - размножаться
        иначе - двигаться
        */
    }


    public ArrayList<Animal> createRandomLocation() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException { // создание локации с рандомным числом разных животных

        HashMap<Integer, Class> mapAllAnimals = new HashMap<>(); // мапа всех животных
        mapAllAnimals.put(0, Wolf.class);
        mapAllAnimals.put(1, Deer.class);
        mapAllAnimals.put(2, Wolf.class);
        mapAllAnimals.put(3, Deer.class);

        int countAnimalsOnFields = new Random().nextInt(10);     // количество животных в локации

        ArrayList<Animal> result = new ArrayList<>();  // локация

        for (int i = 1; i < countAnimalsOnFields; i++) {  // создать нужное количество животных раз

            // рандомный выбор животного по ключу
            int numberOfAnimal = new Random().nextInt(mapAllAnimals.size());

            Class clazz = mapAllAnimals.get(numberOfAnimal); // получен класс рандомного животного

            //System.out.println(Animal.class.isAssignableFrom(clazz));

            // создать объект зная класс
            Constructor<?> constructor = clazz.getConstructor();

            Animal newInstance = (Animal) constructor.newInstance(); // creating object
            // add in result
            result.add(newInstance);
        }

        return result;
    }

    public ArrayList<Animal> createWithParameter(int countAnimalsOnFields) {

        return null;
    }
}
