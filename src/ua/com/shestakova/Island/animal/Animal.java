package ua.com.shestakova.Island.animal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.shestakova.Island.Statistics;
import ua.com.shestakova.Island.settingIsland.Tools;
import ua.com.shestakova.Island.settingIsland.Location;

import java.util.*;

import static ua.com.shestakova.Island.settingIsland.Island.field;

@Setter
@Getter
@ToString
public abstract class Animal extends Сreature {

    private int speed;
    private double countFoodMax;
    private boolean moved = false;
    private double satiety = 0;
    private double lossSatiety;
    private Map<String, Integer> percent = new HashMap<>();

    //public abstract boolean checkTypeAnimalForEat(Animal animal);


    public void eat(ArrayList<Сreature> сreatures) {
        if (this.getSatiety() <= getCountFoodMax()) {

            Сreature сreatureForEat;
            try {
                сreatureForEat = сreatures.stream()
                        .filter(Сreature::isAlive)
                        .filter(this::checkTypeAnimalForEat)
                        .findAny().get();
            } catch (NoSuchElementException e) {
                return;
            }
            if (checkChanceEating(this, сreatureForEat)) {

                сreatureForEat.setAlive(false);
                setSatiety(Math.min(getSatiety() + сreatureForEat.getWeight(), getCountFoodMax()));

                сreatures.remove(сreatures.indexOf(сreatureForEat));
                 сreatures.trimToSize();
                Statistics.setCountDiedCreatures(Statistics.getCountDiedCreatures() + 1);
               // System.out.println(this.getName() + " съел " + сreatureForEat.getName());
            }
        }
    }

    private boolean checkChanceEating(Animal hunter, Сreature prey) {

        int chance = Tools.getRandomNumber(Tools.MAX_PERCENT_BORD);

        for (String name : hunter.getPercent().keySet()) {
            if (name.equals(prey.getClass().getSimpleName())) {
                int probability = hunter.getPercent().get(name);
                if (probability <= chance) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean move(int width, int height) {

        ArrayList<Сreature> newLocation = getNewField(width, height);

        int countTypeInLoc = Location.getCountTypeInLoc(newLocation, this);

        if (countTypeInLoc < this.getMaxCountTypeInLoc()) {
            newLocation.add(this);
            field[width][height].location.remove(this);
            field[width][height].location.trimToSize();
            setMoved(true);
            return true;
        }
        return false;
    }

    private ArrayList<Сreature> getNewField(int x, int y) {
        int xNew = x;
        int yNew = y;
        if (chanceMoveFree(x, y)) {

            switch (Tools.getRandomNumber(4)) {
                case 0 -> xNew = getWay(x, 1);
                case 1 -> yNew = getWay(y, -1);
                case 2 -> xNew = getWay(x, -1);
                case 3 -> yNew = getWay(y, 1);
            }
        } else if (x < getSpeed()) {
            xNew = getWay(x, 1);
        } else if (y < getSpeed()) {
            yNew = getWay(y, 1);
        } else if (x >= field.length - getSpeed()) {
            xNew = getWay(x, -1);
        } else if (y >= field[x].length - getSpeed()) {
            yNew = getWay(y, -1);
        }
        return field[xNew][yNew].location;
    }

    private boolean chanceMoveFree(int x, int y) {
        return x >= getSpeed() && y >= getSpeed() &&
                x < field.length - getSpeed() && y < field[x].length - getSpeed();
    }

    private int getWay(int x, int index) {
        return x + this.getSpeed() * index;
    }

    public void copy(ArrayList<Сreature> creatures, int countAnimalInLoc) {

            if (countAnimalInLoc > 1) {

                for (Map.Entry entry : Tools.mapAllAnimals.entrySet()) {
                    if (entry.getValue().getClass() == (this).getClass()) {
                        creatures.add(Location.createRandomСreature((int) entry.getKey()));
                        Statistics.setCountNewCreatures(Statistics.getCountNewCreatures() + 1);
                        //  System.out.println(this.getName() + " размножился ");
                    }
                }
            }
        }
}
