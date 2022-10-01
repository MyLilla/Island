package ua.com.shestakova.island.constructorGame;

import com.diogonunes.jcolor.Attribute;
import ua.com.shestakova.island.creature.Creature;
import ua.com.shestakova.island.creature.Plant;
import ua.com.shestakova.island.creature.herbivore.*;
import ua.com.shestakova.island.creature.predator.*;
import ua.com.shestakova.island.exceptions.InputException;

import java.util.*;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Tools {

    public static final int CHOICE_ONE = 1;
    public static final int CHOICE_TWO = 2;
    public static final int MAX_PERCENT_BORD = 101;
    public static final int MAX_SIDE_OF_ISLAND = 100;
    public static final Map<String, Integer> settings = getDefaultSettings();
    public static final int MAX_COUNT_INCORRECT_INPUT_NUMBER = 3;
    public static Map<Integer, Creature> mapAllAnimals = addedOfAllAnimals();

    private static Map<Integer, Creature> addedOfAllAnimals() {
        mapAllAnimals = new HashMap<>();
        mapAllAnimals.put(0, new Wolf());
        mapAllAnimals.put(1, new Deer());
        mapAllAnimals.put(2, new Plant());
        mapAllAnimals.put(3, new Rabbit());
        mapAllAnimals.put(4, new Fox());
        mapAllAnimals.put(5, new Boa());
        mapAllAnimals.put(6, new Bear());
        mapAllAnimals.put(7, new Eagle());
        mapAllAnimals.put(8, new Horse());
        mapAllAnimals.put(9, new Mouse());
        mapAllAnimals.put(10, new Goat());
        mapAllAnimals.put(11, new Sheep());
        mapAllAnimals.put(12, new Boar());
        mapAllAnimals.put(13, new Caterpillar());
        mapAllAnimals.put(14, new Duck());
        mapAllAnimals.put(15, new Buffalo());
        return mapAllAnimals;
    }

    private Tools() {
        throw new IllegalStateException("Utility class");
    }

    public static int getRandomNumber(int board) {
        return new Random().nextInt(board);
    }

    public static int getNumberFromUser(int from, int to) {

        Scanner scanner = new Scanner(System.in);
        int number = 0;
        int attempt = 0;

        try {
            while ((number <= from || number >= to) && MAX_COUNT_INCORRECT_INPUT_NUMBER >= attempt) {
                number = scanner.nextInt();
                if (number < from || number > to) {
                    System.out.println("Something is wrong, please try again:");
                    attempt++;
                } else {
                    break;
                }
            }
            if (MAX_COUNT_INCORRECT_INPUT_NUMBER <= attempt) {
                System.out.println(colorize("You have exceeded the input limit, Adios!", Attribute.RED_TEXT()));
                throw new InputException("input limit was exceeded");
            }
        } catch (InputMismatchException e) {
            System.out.println(colorize("You didn't enter a number, Adios!", Attribute.RED_TEXT()));
            throw new InputException("not number was input");
        }
        return number;
    }

    private static Map<String, Integer> getDefaultSettings() {
        Map<String, Integer> settings = new HashMap<>();
        settings.put("WIDTH", 100);
        settings.put("HEIGHT", 20);
        settings.put("MAX_COUNT_IN_LOCATION", 20);
        settings.put("MAX_COUNT_INCORRECT_INPUT_NUMBER", 3);
        settings.put("TIME_OF_GAME", 10);

        return settings;
    }
}
