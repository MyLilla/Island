package ua.com.shestakova.Island.settingIsland;

import ua.com.shestakova.Island.animal.Сreature;
import ua.com.shestakova.Island.exceptions.CreateException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Location {

    public ArrayList<Сreature> location;

    public void createLocation(int maxCharacter) {

        location = new ArrayList<>();

        for (int i = 1; i <= Tools.getRandomNumber(maxCharacter); i++) {
            int numberOfCreature = new Random().nextInt(Tools.mapAllAnimals.size());

            Сreature randomCreature = createRandomСreature(numberOfCreature);

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

    public static int getCountTypeInLoc(ArrayList<Сreature> сreatures, Сreature сreature) {
        int countTypeInLoc = 0;
        for (Сreature сreature1 : сreatures) {
            if (сreature1.getName().equals(сreature.getName())) {
                countTypeInLoc++;
            }
        }
        return countTypeInLoc;
    }

    public static Сreature createRandomСreature(int creatureNumber) {

        Сreature character = Tools.mapAllAnimals.get(creatureNumber);
        Class clazz = character.getClass();
        Constructor<?> constructor;
        try {
            constructor = clazz.getConstructor();
            return (Сreature) constructor.newInstance();

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new CreateException("Random creature creation error" + e);
        }
    }
}
