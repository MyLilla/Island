package ua.com.shestakova.Island.settingIsland;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import ua.com.shestakova.Island.animal.Сreature;
import ua.com.shestakova.Island.exceptions.ParsingExceptions;

import java.io.FileWriter;
import java.io.IOException;

public class Parser {
    private final File PROP_SOURCE = new File("src/ua/com/shestakova/Island/resources/app.properties");
    private final File JSON_SOURCE = new File("src/ua/com/shestakova/Island/resources/app.json");


    public void writerAllAnimalToJson() {

        JSONObject obj = new JSONObject();
        HashMap<Integer, Сreature> allAnimal = Tools.mapAllAnimals;

        for (Map.Entry entry : allAnimal.entrySet()) {

            String name = entry.getValue().getClass().getSimpleName();
            Сreature animal = (Сreature) entry.getValue();

            JSONObject obj2 = new JSONObject();
            obj2.put("icon", animal.getIcon());
            obj2.put("name", animal.getName());
            obj2.put("weight", animal.getWeight());
            obj2.put("maxCountTypeInLoc", animal.getMaxCountTypeInLoc());

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
        // по плану должна брать данные, и в каждом животном не через set заполнять, а из документа
    }

    public void getParametersFromProperties(Island island) {
        try {
            FileReader reader = new FileReader(PROP_SOURCE);
            Properties properties = new Properties();
            properties.load(reader);

            island.setWidth(Integer.parseInt(properties.getProperty("WIDTH")));
            island.setHeight(Integer.parseInt(properties.getProperty("HEIGHT")));
            island.setMaxCountInLocation(Integer.parseInt(properties.getProperty("MAX_COUNT_IN_LOCATION")));
            Tools.maxCountIncorrectInputNumber = (Integer.parseInt(properties.getProperty("MAX_COUNT_INCORRECT_INPUT_NUMBER")));
            Tools.daysOfGame = (Integer.parseInt(properties.getProperty("TIME_OF_GAME")));

        } catch (NumberFormatException e) {
            throw new ParsingExceptions("В файле app.properties какой-то косяк " + e);
        } catch (FileNotFoundException ex) {
            throw new ParsingExceptions("Похоже, файл app.properties не существует " + ex);
        } catch (IOException ex) {
            throw new ParsingExceptions("Что-то пошло не так при чтении данных из app.properties " + ex);
        }
    }
}
