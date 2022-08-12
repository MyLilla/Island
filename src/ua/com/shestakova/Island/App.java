package ua.com.shestakova.Island;

import ua.com.shestakova.Island.Animal.Animal;
import ua.com.shestakova.Island.FieldOfIsland.Island;
import ua.com.shestakova.Island.FieldOfIsland.Location;

import static ua.com.shestakova.Island.FieldOfIsland.Island.fields;

public class App {
    public static void main(String[] args) {
        Island island = new Island(); // создан объект острова
        island.autoAddInland(); // заполнить локациями
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                System.out.print("[" + i + j + "]" ); // ячейка
                for (int k = 0; k < fields[i][j].location.size(); k++) { // лист
                     System.out.print("{"+ fields[i][j].location.get(k).getIcone() + "}");  // животное
                }
            }
            System.out.println();
        }
        System.out.println("_____________________________________");

       tact();

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                System.out.print("[" + i + j + "]" ); // ячейка
                for (int k = 0; k < fields[i][j].location.size(); k++) { // лист
                    System.out.print("{"+ fields[i][j].location.get(k).getIcone() + "}");  // животное
                }
            }
            System.out.println();
        }
    }

    public static void tact () {


        Animal animal = fields[5][5].location.get(1);
       // int step = animal.move(i, j); // с какого места двигаться
        Location otherLocation = fields[5 + animal.getSpeed()][5]; // куда
        otherLocation.location.add(animal); // переселение
        fields[5][5].location.remove(animal);
        fields[5][5].location.trimToSize();

    }
    }

