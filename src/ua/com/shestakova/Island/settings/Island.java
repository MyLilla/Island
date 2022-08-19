package ua.com.shestakova.Island.settings;

import lombok.Getter;
import lombok.Setter;

public class Island {

    private static Island island;

    public static Island getIsland() {
        if (island == null) {
            island = new Island();
        }
        return island;
    }
    private Island() {
    }

    public static Location[][] field;
    @Getter
    @Setter
    public int WIDTH = 10;
    @Getter
    @Setter
    public int HEIGHT = 10;
    @Getter
    @Setter
    public int MAX_COUNT_IN_LOCATION = 10;

    public void addLocationOnIsland(int width, int height) {

        field = new Location[width][height];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                    Location loc = new Location();
                    loc.createLocation(MAX_COUNT_IN_LOCATION);
                    field[i][j] = loc;
            }
        }
    }
}
