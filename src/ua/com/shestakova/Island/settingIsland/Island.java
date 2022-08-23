package ua.com.shestakova.Island.settingIsland;

import lombok.Getter;
import lombok.Setter;

public class Island {

    private static Island island;
    public final int MAX_SIDE_OF_ISLAND = 100;

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
                Location loc = new Location();
                loc.createLocation(maxCountInLocation);
                field[i][j] = loc;
            }
        }
    }
}
