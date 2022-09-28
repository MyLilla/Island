package ua.com.shestakova.island.creature;

import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.island.Dialog;

import java.util.List;

public class Plant extends Creature {
    @Getter
    @Setter
    private int countDaysLife = 3;

    public Plant() {
        setIcon("\uD83C\uDF31");
        setWeight(1);
        setMaxCountTypeInLoc(200);
    }

    @Override
    public boolean checkTypeAnimalForEat(Creature creature) {
        return false;
    }

    public void copy(List<Creature> creatures, int contTypeInLocation) {
        creatures.add(new Plant());
        Dialog.statistics.setCountNewCreatures(Dialog.statistics.getCountNewCreatures() + 1);
    }
}
