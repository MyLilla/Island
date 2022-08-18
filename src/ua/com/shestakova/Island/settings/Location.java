package ua.com.shestakova.Island.settings;

import ua.com.shestakova.Island.Date;
import ua.com.shestakova.Island.animal.Animal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Location {

    public ArrayList<Animal> location;

    public void createRandomLocation() throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException { // создание локации с рандомным числом разных животных

        location = new ArrayList<>();
        int countLifesOnLocation = new Random().nextInt(10);

        for (int i = 1; i <= countLifesOnLocation; i++) {
            int numberOfAnimal = new Random().nextInt(Date.mapAllAnimals.size());  // рандомный выбор животного по ключу

            Animal randomAnimal = createNewAnimal(numberOfAnimal);

            if (location.isEmpty()) {
                location.add(randomAnimal);
            } else {
                int countTypeInLocation = getCountTypeInLoc(location, randomAnimal);
                if (countTypeInLocation < randomAnimal.getMaxCountTypeInLoc()) {
                    location.add(randomAnimal);
                }
            }
        }
    }
    public static int getCountTypeInLoc(ArrayList<Animal> animals, Animal animal) {
        int countTypeInLoc = 0;                         // подсчитать сколько таких животных на клетке уже есть
        for (Animal ani : animals) {
            if (ani.getName().equals(animal.getName())){
                countTypeInLoc++;
            }
        }
        return countTypeInLoc;
    }

    public static Animal createNewAnimal(int animalNumber)  {

        Animal animal = Date.mapAllAnimals.get(animalNumber);
        Class clazz = animal.getClass();
        Constructor<?> constructor;
        try {
            constructor = clazz.getConstructor();
            return (Animal) constructor.newInstance();

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Animal> createWithParameter(int countAnimalsOnFields) {
        return null;
    }

}
