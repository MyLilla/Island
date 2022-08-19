package ua.com.shestakova.Island.settings;

public class Island {
    public static Location[][] field;
    public int WIDTH = 10;
    public int HEIGHT = 10;
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

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getMAX_COUNT_IN_LOCATION() {
        return MAX_COUNT_IN_LOCATION;
    }

    public void setMAX_COUNT_IN_LOCATION(int MAX_COUNT_IN_LOCATION) {
        this.MAX_COUNT_IN_LOCATION = MAX_COUNT_IN_LOCATION;
    }
}
