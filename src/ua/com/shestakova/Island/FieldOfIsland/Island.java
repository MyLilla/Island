package ua.com.shestakova.Island.FieldOfIsland;


import ua.com.shestakova.Island.StartSimulation;
import java.lang.reflect.InvocationTargetException;

public class Island implements StartSimulation {
    public static Location[][] fields;

    // заполнение острова локациями
    public void autoAddInland() {  // авто

        fields = new Location[100][20];  // стандартный

        // заполнены растительностью и животными рандомно
        // надо учесть максимальное количество вида
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                try {
                    Location location = new Location();
                    location.createRandomLocation();
                    fields[i][j] = location;
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
