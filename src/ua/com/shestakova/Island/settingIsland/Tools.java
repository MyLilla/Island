package ua.com.shestakova.Island.settingIsland;

import com.diogonunes.jcolor.Attribute;
import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.herbivore.*;
import ua.com.shestakova.Island.animal.predator.*;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Tools {

    public static int maxCountIncorrectInputNumber = 3;
    public static final int MAX_PERCENT_BORD = 101;
    public static int timeOfGame = 10;
    public static HashMap<Integer, Animal> mapAllAnimals = addedOfAllAnimals();

    public static HashMap<Integer, Animal> addedOfAllAnimals() {
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

    public static int getRandomNumber(int board) {
        return new Random().nextInt(board);
    }

    public static int getNumberFromUser(int from, int to) {

        Scanner scanner = new Scanner(System.in);
        int number = 0;
        int attempt = 0;

        try {
            while ((number <= from || number >= to) && maxCountIncorrectInputNumber >= attempt) {
                number = scanner.nextInt();
                if (number < from || number > to) {
                    System.out.println("Что-то пошло не так, попробуйте еще: ");
                    attempt++;
                } else {
                    break;
                }
            }
            if (maxCountIncorrectInputNumber <= attempt) {
                System.out.println(colorize("Вы превысили лимит ввода, Adios!", Attribute.RED_TEXT()));
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println(colorize("Вы ввели не число, Adios!", Attribute.RED_TEXT()));
            System.exit(0);
        }
        return number;
    }
}
