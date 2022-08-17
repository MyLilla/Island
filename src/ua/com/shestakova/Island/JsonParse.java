package ua.com.shestakova.Island;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.com.shestakova.Island.animal.Animal;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonParse {

    private final File JSON_SOURCE = new File ("src/ua/com/shestakova/animal.json");
    ObjectMapper mapper = new ObjectMapper();

    public void parserToJsonMAP(HashMap<Integer, Animal> allAnimals) { // записываются в файл все животные из списка

        mapper.enable(SerializationFeature.INDENT_OUTPUT);  // выравнивание строк
        try {
            mapper.writeValue(JSON_SOURCE, allAnimals);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void parseJason () {

        Map <Animal, Integer> result = new HashMap<>();
    }
}
