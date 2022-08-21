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

    public void eat(ArrayList<Animal> animals) {
        if (this.getSatiety() <= getCountFoodMax()) {

            Animal ani;
            try {
                ani = animals.stream()
                        .filter(Animal::isAlive)
                        .filter(this::checkTypeAnimalForEat)
                        .findAny().get();
            } catch (NoSuchElementException e) {
                return;
            }
            if (checkChanceEating(this, ani)) {
                ani.setAlive(false);

                setSatiety(Math.min(getSatiety() + ani.getWeight(), getCountFoodMax()));

                animals.remove(animals.indexOf(ani));
                animals.trimToSize();
                Statistics.setCountDiedAnimal(Statistics.getCountDiedAnimal() + 1);
            }
        }
    }

    private boolean checkChanceEating(Animal hunter, Animal prey) {

        int chance = Tools.getRandomNumber(101);

        for (String name : hunter.getPercent().keySet()) {          // проход по всем животным (key)
            if (name.equals(prey.getClass().getSimpleName())) {     // найти подходящего класса
                int probability = hunter.getPercent().get(name);   // взять его процент
                if (probability <= chance) {                      //  сравнить с выпавшим
                    return true;
                }
            }
        }
        return false;
    }

    public boolean move(int x, int y) {

        ArrayList<Animal> newLocation = getNewField(x, y);

        int count = Location.getCountTypeInLoc(newLocation, this); // сколько животных такого типа на локации

        if (count < this.getMaxCountTypeInLoc()) {
            newLocation.add(this);
            field[x][y].location.remove(this);
            field[x][y].location.trimToSize();
            setMoved(true);
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<Animal> getNewField(int x, int y) {
        int xNew = x;
        int yNew = y;
        if (x >= getSpeed() && y >= getSpeed() &&
                x < field.length - getSpeed() && y < field[x].length - getSpeed()) {

            switch (Tools.getRandomNumber(4)) {
                case 0 -> xNew = down(x);
                case 1 -> yNew = right(y);
                case 2 -> xNew = up(x);
                case 3 -> yNew = left(y);
            }
        } else if (x < getSpeed()) {
            xNew = down(x);
        } else if (y < getSpeed()) {
            yNew = left(y);
        } else if (x >= field.length - getSpeed()) {
            xNew = up(x);
        } else if (y >= field[x].length - getSpeed()) {
            yNew = right(y);
        }
        return field[xNew][yNew].location;
    }

    private int down(int x) {
        return x + this.getSpeed();
    }

    private int up(int x) {
        return x - this.getSpeed();
    }

    private int left(int y) {
        return y + this.getSpeed();
    }

    private int right(int y) {
        return y - this.getSpeed();
    }

    public void copy(ArrayList<Animal> animals) {

        if (getChanceMakeCopy() < Tools.getRandomNumber(101)) {

            int countTypeInLoc = Location.getCountTypeInLoc(animals, this);

            if (countTypeInLoc < getMaxCountTypeInLoc() &&
                    (countTypeInLoc > 1 || this.getClass().equals(Plant.class))) {

                Animal newAni;
                for (Map.Entry entry : Tools.mapAllAnimals.entrySet()) {
                    if (entry.getValue().getClass() == (this).getClass()) {

                        newAni = Location.createRandomAnimal((int) entry.getKey());
                        animals.add(newAni);
                        break;
                    }
                }
            }
        }
    }

    public void die(int x, int y) {
        if (!isAlive()) {
            field[x][y].location.remove(this);
            field[x][y].location.trimToSize();
            setAlive(false);
            Statistics.setCountDiedAnimal(Statistics.getCountDiedAnimal() + 1);
        }
    }
}

