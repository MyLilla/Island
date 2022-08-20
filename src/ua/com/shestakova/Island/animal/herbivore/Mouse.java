package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Herbivore;
import ua.com.shestakova.Island.animal.Plant;

public class Mouse extends Herbivore {

    public Mouse() {
        setIcon("\uD83D\uDC01");
        setWeight(0.05);
        setMaxCountTypeInLoc(500);
        setSpeed(1);
        setCountFoodMax(0.01);
        setLossSatiety(0.005);
        getPercent().put("Caterpillar", 90);
    }
    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return animal instanceof Plant ||
                animal instanceof Caterpillar;
    }
}
