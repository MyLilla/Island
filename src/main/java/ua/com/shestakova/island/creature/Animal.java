package ua.com.shestakova.island.creature;

import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.island.Dialog;
import ua.com.shestakova.island.Statistics;
import ua.com.shestakova.island.constructorGame.Tools;
import ua.com.shestakova.island.constructorGame.Location;

import java.util.*;

import static ua.com.shestakova.island.constructorGame.Island.field;

@Setter
@Getter
public abstract class Animal extends Creature {

    private boolean moved = false;
    private int speed;
    private double countFoodMax;
    private double satiety;
    private double lossSatiety;
    private Map<String, Integer> percent = new HashMap<>();

    public void eat(ArrayList<Creature> creatures) {

        if (this.getSatiety() <= getCountFoodMax()) {

            Creature creatureForEat;
            try {
                creatureForEat = creatures.stream()
                        .filter(Creature::isAlive)
                        .filter(this::checkTypeAnimalForEat)
                        .findAny().get();
            } catch (NoSuchElementException e) {
                return;
            }
            if (checkChanceEating(this, creatureForEat)) {

                creatureForEat.setAlive(false);
                setSatiety(Math.min(getSatiety() + creatureForEat.getWeight(), getCountFoodMax()));

                creatures.remove(creatures.indexOf(creatureForEat));
                creatures.trimToSize();
                Dialog.statistics.setCountDiedCreatures(Dialog.statistics.getCountDiedCreatures() + 1);
            }
        }
    }

    private boolean checkChanceEating(Animal hunter, Creature prey) {

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

        ArrayList<Creature> locationForMove = getNewField(width, height);

        int countTypeInLoc = Location.getCountTypeInLoc(locationForMove, this);

        if (countTypeInLoc < this.getMaxCountTypeInLoc()) {
            locationForMove.add(this);
            field[width][height].location.remove(this);
            field[width][height].location.trimToSize();
            setMoved(true);
            return true;
        }
        return false;
    }

    private ArrayList<Creature> getNewField(int width, int height) {
        int widthNew = width;
        int heightNew = height;
        if (chanceMoveFree(width, height)) {

            switch (Tools.getRandomNumber(4)) {
                case 0 -> widthNew = getWay(width, 1);
                case 1 -> heightNew = getWay(height, -1);
                case 2 -> widthNew = getWay(width, -1);
                case 3 -> heightNew = getWay(height, 1);
            }
        } else if (width < getSpeed()) {
            widthNew = getWay(width, 1);
        } else if (height < getSpeed()) {
            heightNew = getWay(height, 1);
        } else if (width >= field.length - getSpeed()) {
            widthNew = getWay(width, -1);
        } else if (height >= field[width].length - getSpeed()) {
            heightNew = getWay(height, -1);
        }
        return field[widthNew][heightNew].location;
    }

    private boolean chanceMoveFree(int x, int y) {
        return x >= getSpeed() && y >= getSpeed() &&
                x < field.length - getSpeed() && y < field[x].length - getSpeed();
    }

    private int getWay(int x, int index) {
        return x + this.getSpeed() * index;
    }

    public void copy(List<Creature> creatures, int countAnimalInLoc) {

        if (countAnimalInLoc > 1) {

            for (Map.Entry entry : Tools.mapAllAnimals.entrySet()) {
                if (entry.getValue().getClass() == (this).getClass()) {
                    creatures.add(Location.createRandomСreature((int) entry.getKey()));
                    Dialog.statistics.setCountNewCreatures(Dialog.statistics.getCountNewCreatures() + 1);
                }
            }
        }
    }
}
