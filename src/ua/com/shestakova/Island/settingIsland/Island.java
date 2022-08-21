package ua.com.shestakova.Island.settingIsland;

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
    private int WIDTH = 20;
    @Getter
    @Setter
    private int HEIGHT = 100;
    @Getter
    @Setter
    private int MAX_COUNT_IN_LOCATION = 50;

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
