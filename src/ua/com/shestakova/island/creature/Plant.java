package ua.com.shestakova.island.creature;

import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.island.Statistics;

import java.util.ArrayList;

public class Plant extends Сreature {
    @Getter
    @Setter
    private int countDaysLife = 3;

    public Plant() {
        setIcon("\uD83C\uDF31");
        setWeight(1);
        setMaxCountTypeInLoc(200);
    }

    @Override
    public boolean checkTypeAnimalForEat(Сreature сreature) {
        return false;
    }

    public void copy(ArrayList<Сreature> creatures, int contTypeInLocation) {
        creatures.add(new Plant());
        Statistics.setCountNewCreatures(Statistics.getCountNewCreatures() + 1);
       // System.out.println("появилось новое растение");
    }
}
