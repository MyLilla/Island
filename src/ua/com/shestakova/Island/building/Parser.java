package ua.com.shestakova.Island.building;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import ua.com.shestakova.Island.settingsActions.Time;
import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.exceptions.ParsingExceptions;

import java.io.FileWriter;
import java.io.IOException;

public class Parser {
    private final File PROP_SOURCE = new File("src/ua/com/shestakova/app.properties");
    private final File JSON_SOURCE = new File("src/ua/com/shestakova/app.json");

    public void writerToJson() {

        JSONObject obj = new JSONObject();
        HashMap<Integer, Animal> allAnimal = Tools.mapAllAnimals;

        for (Map.Entry entry : allAnimal.entrySet()) {

            String name = entry.getValue().getClass().getSimpleName();
            Animal animal = (Animal) entry.getValue();

            JSONObject obj2 = new JSONObject();
            obj2.put("icon", animal.getIcon());
            obj2.put("name", animal.getName());
            obj2.put("weight", animal.getWeight());
            obj2.put("maxCountTypeInLoc", animal.getMaxCountTypeInLoc());
            obj2.put("speed", animal.getSpeed());
            obj2.put("countFoodMax", animal.getCountFoodMax());
            obj2.put("lossSatiety", animal.getLossSatiety());
            obj2.put("percent", animal.getCountFoodMax());

            obj.put(name, obj2);

        }
        try {
            FileWriter file = new FileWriter(JSON_SOURCE);
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readerFromJson() {
        // получить данные из файла - пока не могу
    }

    public void getParametersFromProperties(Island island) {
        try {
            FileReader reader = new FileReader(PROP_SOURCE);
            Properties properties = new Properties();
            properties.load(reader);

            island.setWIDTH(Integer.parseInt(properties.getProperty("WIDTH")));
            island.setHEIGHT(Integer.parseInt(properties.getProperty("HEIGHT")));
            island.setMAX_COUNT_IN_LOCATION(Integer.parseInt(properties.getProperty("MAX_COUNT_IN_LOCATION")));
            Tools.MAX_COUNT_INCORRECT_INPUT_NUMBER = (Integer.parseInt(properties.getProperty("MAX_COUNT_INCORRECT_INPUT_NUMBER")));
            Time.TIME_OF_GAME = (Integer.parseInt(properties.getProperty("TIME_OF_GAME")));

        } catch (NumberFormatException e) {
            throw new ParsingExceptions("В файле app.properties какой-то косяк " + e);
        } catch (FileNotFoundException ex) {
            throw new ParsingExceptions("Похоже, файл app.properties не существует " + ex);
        } catch (IOException ex) {
            throw new ParsingExceptions("Что-то пошло не так при чтении данных из app.properties " + ex);
        }
    }

}
