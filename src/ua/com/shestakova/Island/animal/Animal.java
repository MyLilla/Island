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
public abstract class Animal {

    private String icon;
    private String name = this.getClass().getSimpleName();
    private double weight;
    private int maxCountTypeInLoc;
    private int speed;
    private double countFoodMax;
    private boolean moved = false;
    private double satiety = 0;
    private double lossSatiety;
    private boolean alive = true;
    private int chanceMakeCopy = 50;
    private Map<String, Integer> percent = new HashMap<>();

    public abstract boolean checkTypeAnimalForEat(Animal animal);

    public synchronized void eat(ArrayList<Animal> animals) {
        if (this.getSatiety() <= getCountFoodMax()) {

            Animal animal;
            try {
                animal = animals.stream()
                        .filter(Animal::isAlive)
                        .filter(this::checkTypeAnimalForEat)
                        .findAny().get();
            } catch (NoSuchElementException e) {
                return;
            }
            if (checkChanceEating(this, animal)) {
                animal.setAlive(false);

                setSatiety(Math.min(getSatiety() + animal.getWeight(), getCountFoodMax()));

                animals.remove(animals.indexOf(animal));
                animals.trimToSize();
                Statistics.setCountDiedAnimal(Statistics.getCountDiedAnimal() + 1);
            }
        }
    }

    private boolean checkChanceEating(Animal hunter, Animal prey) {

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

        ArrayList<Animal> newLocation = getNewField(width, height);

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

    private ArrayList<Animal> getNewField(int x, int y) {
        int xNew = x;
        int yNew = y;
        if (chanceMoveFree(x, y)) {

            switch (Tools.getRandomNumber(4)) {
                case 0 -> xNew = newValue(x, 1);
                case 1 -> yNew = newValue(y, -1);
                case 2 -> xNew = newValue(x, -1);
                case 3 -> yNew = newValue(y, 1);
            }
        } else if (x < getSpeed()) {
            xNew = newValue(x, 1);
        } else if (y < getSpeed()) {
            yNew = newValue(y, 1);
        } else if (x >= field.length - getSpeed()) {
            xNew = newValue(x, -1);
        } else if (y >= field[x].length - getSpeed()) {
            yNew = newValue(y, -1);
        }
        return field[xNew][yNew].location;
    }

    private boolean chanceMoveFree(int x, int y) {
        return x >= getSpeed() && y >= getSpeed() &&
                x < field.length - getSpeed() && y < field[x].length - getSpeed();
    }

    private int newValue(int x, int index) {
        return x + this.getSpeed() * index;
    }

    public void copy(ArrayList<Animal> animals) {

        if (getChanceMakeCopy() < Tools.getRandomNumber(Tools.MAX_PERCENT_BORD)) {

            int countTypeInLoc = Location.getCountTypeInLoc(animals, this);

            if (countTypeInLoc < getMaxCountTypeInLoc() &&
                    (countTypeInLoc > 1 || this.getClass().equals(Plant.class))) {

                for (Map.Entry entry : Tools.mapAllAnimals.entrySet()) {
                    if (entry.getValue().getClass() == (this).getClass()) {

                        animals.add(Location.createRandomAnimal((int) entry.getKey()));
                        break;
                    }
                }
            }
        }
    }

    public void utilize(int x, int y) {
        if (!isAlive()) {
            field[x][y].location.remove(this);
            field[x][y].location.trimToSize();
            setAlive(false);
            Statistics.setCountDiedAnimal(Statistics.getCountDiedAnimal() + 1);
        }
    }
}
