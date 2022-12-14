package ua.com.shestakova.island.constructorGame;

import lombok.Getter;
import lombok.Setter;

public class Island {

    public static Location[][] field;
    @Getter
    @Setter
    private int width;
    @Getter
    @Setter
    private int height;
    @Getter
    @Setter
    private int maxCountInLocation;

    public void addLocationOnIsland(int width, int height) {

        field = new Location[width][height];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                Location location = new Location();
                location.createLocation(maxCountInLocation);
                field[i][j] = location;
            }
        }
    }
}
