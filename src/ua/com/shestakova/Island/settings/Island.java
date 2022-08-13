package ua.com.shestakova.Island.settings;


import ua.com.shestakova.Island.StartSimulation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Island implements StartSimulation {
    public static Location[][] field;
    private final int WIDTH = 10; // столбец
    private final int HEIGHT = 10; // строка

    // заполнение острова локациями
    public void autoAddInland() {  // авто

        field = new Location[WIDTH][HEIGHT];  // стандартный

        // заполнены растительностью и животными рандомно

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                try {
                    Location loc = new Location();
                    loc.createRandomLocation();
                    field[i][j] = loc;
//
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    // класс состояния каждого поля?
    @Override
    public void withSittingsIsland() {

    }

    @Override
    public void startIsland() {

    }
}
