package ua.com.shestakova.island.constructorGame;


import org.json.simple.JSONObject;

import java.awt.print.PrinterException;
import java.io.*;
import java.util.Map;
import java.util.Properties;

import ua.com.shestakova.island.creature.Creature;
import ua.com.shestakova.island.exceptions.ParsingExceptions;
import ua.com.shestakova.island.performingActions.Simulation;

import java.io.FileWriter;
import java.io.IOException;

public class Parser {
    private final File PROP_SOURCE = new File("src/main/resources/settingIsland.properties");
    private final File JSON_SOURCE = new File("src/main/resources/allAnimal.json");


    public void writerAllAnimalToJson() throws PrinterException {

        JSONObject creatures = new JSONObject();
        Map<Integer, Creature> allAnimal = Tools.mapAllAnimals;

        for (Map.Entry entry : allAnimal.entrySet()) {

            String name = entry.getValue().getClass().getSimpleName();
            Creature animal = (Creature) entry.getValue();

            JSONObject creature = new JSONObject();
            creature.put("icon", animal.getIcon());
            creature.put("name", animal.getName());
            creature.put("weight", animal.getWeight());
            creature.put("maxCountTypeInLoc", animal.getMaxCountTypeInLoc());

            creatures.put(name, creature);
        }
        try (FileWriter file = new FileWriter(JSON_SOURCE)) {
            file.write(creatures.toJSONString());
            file.flush();
        } catch (IOException e) {
            throw new PrinterException("JSON writing exception " + e + JSON_SOURCE);
        }
    }

    public void getParametersFromProperties(Island island) {
        try (FileReader reader = new FileReader(PROP_SOURCE)) {
            Properties properties = new Properties();
            properties.load(reader);

            island.setWidth(Integer.parseInt(properties.getProperty("WIDTH")));
            island.setHeight(Integer.parseInt(properties.getProperty("HEIGHT")));
            island.setMaxCountInLocation(Integer.parseInt(properties.getProperty("MAX_COUNT_IN_LOCATION")));
            Simulation.timeOfGame = (Integer.parseInt(properties.getProperty("TIME_OF_GAME")));

        } catch (NumberFormatException e) {
            throw new ParsingExceptions("File settingIsland.properties exception " + e + PROP_SOURCE);
        } catch (FileNotFoundException ex) {
            throw new ParsingExceptions("File settingIsland.properties не существует is not exist" + ex + PROP_SOURCE);
        } catch (IOException ex) {
            throw new ParsingExceptions("Something wrong while reading data from settingIsland.properties " + ex + PROP_SOURCE);
        }
    }

    public void writeParametersToProperties(Map<String, Integer> settings) {

            try (OutputStream output = new FileOutputStream(PROP_SOURCE)) {
                Properties properties = new Properties();

                for (Map.Entry entry : settings.entrySet()) {
                    properties.setProperty(entry.getKey().toString(), entry.getValue().toString());
                }
                properties.store(output, null);

        } catch (NumberFormatException e) {
            throw new ParsingExceptions("File settingIsland.properties exception " + e + PROP_SOURCE);
        } catch (FileNotFoundException ex) {
            throw new ParsingExceptions("File settingIsland.properties не существует is not exist" + ex + PROP_SOURCE);
        } catch (IOException ex) {
            throw new ParsingExceptions("Something wrong while reading data from settingIsland.properties " + ex + PROP_SOURCE);
        }
    }
}
