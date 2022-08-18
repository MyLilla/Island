package ua.com.shestakova.Island;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.herbivore.Deer;
import ua.com.shestakova.Island.animal.predator.Wolf;
import ua.com.shestakova.Island.settings.Island;
import ua.com.shestakova.Island.settings.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import static ua.com.shestakova.Island.Tact.makeTact;

public class App {

    public static void main(String[] args) {

        Island island = new Island(); // создан объект острова
        island.addLocationOnIsland(island.WIDTH, island.HEIGHT); // заполнить локациями

//        JsonParse parse = new JsonParse();
//        parse.parserToJsonMAP(Date.mapAllAnimals);

        makeTact();

//       File JSON_SOURCE = new File ("src/ua/com/shestakova/app.properties");
//        try {
//            FileReader reader = new FileReader (JSON_SOURCE);
//            Properties properties = new Properties();
//            properties.load(reader);
//            String appVersion = properties.getProperty("version");
//            System.out.println(appVersion);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}


