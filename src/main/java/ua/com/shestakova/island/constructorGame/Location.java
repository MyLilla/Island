package ua.com.shestakova.island.constructorGame;

import ua.com.shestakova.island.creature.Creature;
import ua.com.shestakova.island.exceptions.CreateException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Location {

    public ArrayList<Creature> location;

    public void createLocation(int maxCharacter) {

        location = new ArrayList<>();

        for (int i = 1; i <= Tools.getRandomNumber(maxCharacter + 1); i++) {
            int numberOfCreature = new Random().nextInt(Tools.mapAllAnimals.size());

            Creature randomCreature = createRandomСreature(numberOfCreature);

            if (location.isEmpty()) {
                location.add(randomCreature);
            } else {
                int countTypeInLocation = getCountTypeInLoc(location, randomCreature);
                if (countTypeInLocation < randomCreature.getMaxCountTypeInLoc()) {
                    location.add(randomCreature);
                }
            }
        }
    }

    public static int getCountTypeInLoc(ArrayList<Creature> сreatures, Creature сreature) {
        int countTypeInLoc = 0;
        for (Creature сreature1 : сreatures) {
            if (сreature1.getName().equals(сreature.getName())) {
                countTypeInLoc++;
            }
        }
        return countTypeInLoc;
    }

    public static Creature createRandomСreature(int creatureNumber) {

        Creature character = Tools.mapAllAnimals.get(creatureNumber);
        Class clazz = character.getClass();
        Constructor<?> constructor;
        try {
            constructor = clazz.getConstructor();
            return (Creature) constructor.newInstance();

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new CreateException("Random creature creation error" + e);
        }
    }
}
