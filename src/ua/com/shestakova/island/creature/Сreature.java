package ua.com.shestakova.island.creature;

import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.island.Statistics;

import java.util.ArrayList;

import static ua.com.shestakova.island.constructorGame.Island.field;

@Setter
@Getter
public abstract class Сreature {

    private String icon;
    private String name = this.getClass().getSimpleName();
    private double weight;
    private int maxCountTypeInLoc;
    private boolean alive = true;
    private int chanceMakeCopy = 70;

    public abstract boolean checkTypeAnimalForEat(Сreature animal);

    public abstract void copy(ArrayList<Сreature> creatures, int contTypeInLocation);

    public synchronized void utilize(int x, int y) {
        if (!isAlive()) {
            field[x][y].location.remove(this);
            field[x][y].location.trimToSize();
            setAlive(false);
            Statistics.setCountDiedCreatures(Statistics.getCountDiedCreatures() + 1);
        }
    }
}
