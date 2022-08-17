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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static ua.com.shestakova.Island.Tact.makeTact;

public class App {

    public static void main(String[] args) {


        Island island = new Island(); // создан объект острова
        island.addLocationOnIsland(); // заполнить локациями

//        JsonParse parse = new JsonParse();
//        parse.parserToJsonMAP(Date.mapAllAnimals);

        makeTact();
          }
}


