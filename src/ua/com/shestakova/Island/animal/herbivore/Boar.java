package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Herbivore;
import ua.com.shestakova.Island.animal.Plant;
import ua.com.shestakova.Island.animal.Сreature;

public class Boar extends Herbivore {

    public Boar() {
        setIcon("\uD83D\uDC17");
        setWeight(400);
        setMaxCountTypeInLoc(50);
        setSpeed(2);
        setCountFoodMax(50);
        setLossSatiety(20);
        getPercent().put("Caterpillar", 90);
        getPercent().put("Mouse", 50);
    }
    @Override
    public boolean checkTypeAnimalForEat(Сreature сreature) {
        return сreature instanceof Plant ||
                сreature instanceof Caterpillar ||
                сreature instanceof Mouse;
    }
}
