package ua.com.shestakova.Island.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.com.shestakova.Island.animal.Animal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Parser {
    private final File JSON_SOURCE = new File ("src/ua/com/shestakova/app.json");
    private final File PROP_SOURCE = new File("src/ua/com/shestakova/app.properties");

    public void parserToJsonMAP(HashMap<Integer, Animal> allAnimals) { // записываются в файл все животные из списка

        ObjectMapper mapper = new ObjectMapper();

        Parser parse = new Parser();
        parse.parserToJsonMAP(Date.mapAllAnimals);


        mapper.enable(SerializationFeature.INDENT_OUTPUT);  // выравнивание строк
        try {
            mapper.writeValue(JSON_SOURCE, allAnimals);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
