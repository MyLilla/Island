package ua.com.shestakova.Island.settings;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.predator.Wolf;

import java.io.FileWriter;
import java.io.IOException;

public class Parser {
    private final File PROP_SOURCE = new File("src/ua/com/shestakova/app.properties");
    private final File JSON_SOURCE = new File("src/ua/com/shestakova/app.json");

    public void writerToJson() {

        JSONObject obj = new JSONObject();

        HashMap<Integer, Animal> allAnimal =  Tools.mapAllAnimals;

        for (Map.Entry entry : allAnimal.entrySet()) {

            String name = entry.getValue().getClass().getSimpleName();
            Animal animal = (Animal) entry.getValue();

            JSONObject obj2 = new JSONObject();
            obj2.put("name", animal.getName());
            obj2.put("countFoodMax", animal.getCountFoodMax());

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
    public void readerFromJson() { // получить данные из файла

        try {
            // считывание файла JSON
            FileReader reader = new FileReader(JSON_SOURCE);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            // получение строки из объекта
            String firstName = (String) jsonObject.get("Wolf");
            System.out.println("The first name is: " + firstName);

//            // получение номера из объекта
//            long id =  (long) jsonObject.get("id");
//            System.out.println("The id is: " + id);

            // получение массива
            JSONArray lang= (JSONArray) jsonObject.get("Bear");

            // берем элементы массива
            for(int i=0; i<lang.size(); i++){
                System.out.println("The " + i + " element of the array: " + lang.get(i));
            }
            Iterator i = lang.iterator();

//            // берем каждое значение из массива json отдельно
//            while (i.hasNext()) {
//                JSONObject innerObj = (JSONObject) i.next();
//                System.out.println("language "+ innerObj.get("lang") +
//                        " with level " + innerObj.get("knowledge"));
//            }
//            // обрабатываем структуру в объекте
//            JSONObject structure = (JSONObject) jsonObject.get("job");
//            System.out.println("Into job structure, name: " + structure.get("name"));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }



    public void getParametersFromProperties(Island island) {
        try {
            FileReader reader = new FileReader(PROP_SOURCE);
            Properties properties = new Properties();
            properties.load(reader);

            island.setWIDTH(Integer.parseInt(properties.getProperty("WIDTH")));
            island.setHEIGHT(Integer.parseInt(properties.getProperty("HEIGHT")));
            island.setMAX_COUNT_IN_LOCATION(Integer.parseInt(properties.getProperty("MAX_COUNT_IN_LOCATION")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
