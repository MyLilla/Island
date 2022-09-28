package ua.com.shestakova.island.creature;

import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.island.Dialog;
import ua.com.shestakova.island.Statistics;

import java.util.ArrayList;
import java.util.List;

import static ua.com.shestakova.island.constructorGame.Island.field;

@Setter
@Getter
public abstract class Creature {

    private String icon;
    private String name = this.getClass().getSimpleName();
    private double weight;
    private int maxCountTypeInLoc;
    private boolean alive = true;
    private int chanceMakeCopy = 70;

    public abstract boolean checkTypeAnimalForEat(Creature animal);

    public abstract void copy(List<Creature> creatures, int contTypeInLocation);

    public synchronized void utilize(int x, int y) {
        if (!isAlive()) {
            field[x][y].location.remove(this);
            field[x][y].location.trimToSize();
            setAlive(false);
            Dialog.statistics.setCountDiedCreatures(Dialog.statistics.getCountDiedCreatures() + 1);
        }
    }
}
