package ua.com.shestakova.Island;


import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.settings.Island;

import static ua.com.shestakova.Island.Tact.makeTact;
import static ua.com.shestakova.Island.settings.Island.field;

public class App {
    public static void main(String[] args) {

//        Animal animal;
//        {
//            try {
//                animal = new ObjectMapper().readValue(new File("src/ua/com/shestakova/date.json"), Animal.class);
//                System.out.println(animal.getWeight());
//
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        Island island = new Island(); // создан объект острова
        island.addLocationOnIsland(); // заполнить локациями

        makeTact();

          }

}


