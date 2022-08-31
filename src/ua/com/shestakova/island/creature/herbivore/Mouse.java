package ua.com.shestakova.island.creature.herbivore;

import ua.com.shestakova.island.creature.Herbivore;
import ua.com.shestakova.island.creature.Plant;
import ua.com.shestakova.island.creature.Сreature;

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
    public boolean checkTypeAnimalForEat(Сreature сreature) {
        return сreature instanceof Plant ||
                сreature instanceof Caterpillar;
    }
}
