package ua.com.shestakova.Island.FieldOfIsland;


import ua.com.shestakova.Island.StartSimulation;
import java.lang.reflect.InvocationTargetException;

public class Island implements StartSimulation {
    public static Location[][] fields;
    private final int WIDTH = 10;
    private final int HEIGHT = 10;

    // заполнение острова локациями
    public void autoAddInland() {  // авто

        fields = new Location[WIDTH][HEIGHT];  // стандартный

        // заполнены растительностью и животными рандомно
        // надо учесть максимальное количество вида
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                try {
                    Location loc = new Location();
                    loc.createRandomLocation();
                    fields[i][j] = loc;
                    for (int k = 0; k < loc.location.size(); k++) {
                        loc.location.get(k).setX(i);
                        loc.location.get(k).setY(j);
                    }
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
