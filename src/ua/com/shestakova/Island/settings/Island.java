package ua.com.shestakova.Island.settings;

import ua.com.shestakova.Island.StartSimulation;
import java.lang.reflect.InvocationTargetException;

public class Island implements StartSimulation {
    public static Location[][] field;
    private final int WIDTH = 10; // столбец
    private final int HEIGHT = 10; // строка

    public void addLocationOnIsland() {   // заполнение острова локациями

        field = new Location[WIDTH][HEIGHT];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                try {
                    Location loc = new Location();
                    loc.createRandomLocation();
                    field[i][j] = loc;
                    // обработать
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @Override
    public void addLocationWithSittingsIsland() {

    }

    @Override
    public void startIsland() {

    }
}
