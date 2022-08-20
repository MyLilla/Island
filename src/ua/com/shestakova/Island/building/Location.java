package ua.com.shestakova.Island.building;

import ua.com.shestakova.Island.animal.Animal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Location {

    public ArrayList<Animal> location;

    public void createLocation(int maxAnimal) {

        location = new ArrayList<>();
      
        for (int i = 1; i <= Tools.getRandomNumber(maxAnimal); i++) {  // количество животных на локации
            int numberOfAnimal = new Random().nextInt(Tools.mapAllAnimals.size());

            Animal randomAnimal = createRandomAnimal(numberOfAnimal);

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

    public static Animal createRandomAnimal(int animalNumber)  {

        Animal animal = Tools.mapAllAnimals.get(animalNumber);
        Class clazz = animal.getClass();
        Constructor<?> constructor;
        try {
            constructor = clazz.getConstructor();
            return (Animal) constructor.newInstance();

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
