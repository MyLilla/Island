package ua.com.shestakova.Island.settings;

import ua.com.shestakova.Island.StartSimulation;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Island implements StartSimulation {
    public static Location[][] field;
    public final int WIDTH = 6; // столбец
    public final int HEIGHT = 6; // строка

    public void addLocationOnIsland(int width, int height) {   // заполнение острова локациями

        field = new Location[width][height];
      //  Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);

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
    public void startIsland() {

    }
}
