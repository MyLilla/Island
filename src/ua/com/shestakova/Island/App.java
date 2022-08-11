package ua.com.shestakova.Island;

import ua.com.shestakova.Island.FieldOfIsland.Island;

public class App {
    public static void main(String[] args) {
        Island island = new Island(); // создан объект острова
        island.autoAddInland(); // заполнить локациями
        for (int i = 0; i < Island.fields.length; i++) {
            for (int j = 0; j < Island.fields[i].length; j++) {
                System.out.println(Island.fields[i][j].toString());
            }
            System.out.println();
        }
    }
}
