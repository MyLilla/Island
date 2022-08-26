package ua.com.shestakova.Island.animal;

import ua.com.shestakova.Island.Statistics;

import java.util.ArrayList;

public class Plant extends Сreature {
    public Plant() {
        setIcon("\uD83C\uDF31");
        setWeight(1);
        setMaxCountTypeInLoc(200);
    }

    @Override
    public boolean checkTypeAnimalForEat(Сreature сreature) {
        return false;
    }

    public void copy(ArrayList<Сreature> сreatures, int contPlantInLocation) {
            сreatures.add(new Plant());
            Statistics.setCountNewCreatures(Statistics.getCountNewCreatures() + 1);
        System.out.println("появилось новое растение");
    }
}
