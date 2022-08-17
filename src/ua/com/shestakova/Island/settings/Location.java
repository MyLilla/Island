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
                if (countTypeInLocation < randomAnimal.getMAX_COUNT_OF_THIS_ANIMAL()) {
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
