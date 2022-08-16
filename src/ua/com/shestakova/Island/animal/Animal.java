package ua.com.shestakova.Island.animal;

import java.util.ArrayList;
import java.util.Random;

import static ua.com.shestakova.Island.settings.Island.field;

public abstract class Animal{

    private String icon;
    private int weight;
    private int maxCountInOneField;
    private int speed;
    private double countFoodMax;
    private boolean moved = false;
    private int satiety = 100;
    private boolean alive = true;

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public abstract int eat(ArrayList <Animal> animals);
    // растения и/или других животных
    // проверка, есть ли подходящая еда


    public boolean move(int x, int y) {

        ArrayList<Animal> newLocation = getNewField(x, y);

        int count = getCountThisTypeInNewLocation(newLocation); // сколько животных такого типа на локации

        if (count < this.getMaxCountInOneField()) {
            newLocation.add(this);             // переселение
            field[x][y].location.remove(this);             // удаление со старой
            field[x][y].location.trimToSize();
            setMoved(true);
            return true;
        } else {
            return false;
        }
    }
    private int getCountThisTypeInNewLocation(ArrayList<Animal> loc) {

        int countThisAnimalInNewLocation = 0;
        for (Animal animal : loc) {
            if (animal.getClass().equals(this.getClass()))
                countThisAnimalInNewLocation++;
        }
        return countThisAnimalInNewLocation;
    }

    private ArrayList<Animal> getNewField(int x, int y) {
        int xNew = x;
        int yNew = y;
        if (x >= getSpeed() && y >= getSpeed() &&
                x < field.length - getSpeed() && y < field[x].length - getSpeed()) {
            int random = new Random().nextInt(4);
            switch (random) {
                case 0 -> xNew = x + getSpeed();
                case 1 -> yNew = y - getSpeed();
                case 2 -> xNew = x - getSpeed();
                case 3 -> yNew = y + getSpeed();
            }
        } else if (x < getSpeed()) {
            xNew = x + getSpeed();
        } else if (y < getSpeed()) {
            yNew = y + getSpeed();
        } else if (x >= field.length - getSpeed()) {
            xNew = x - getSpeed();
        } else if (y >= field[x].length - getSpeed()) {
            yNew = y - getSpeed();
        }
        return field[xNew][yNew].location;
    }

    public abstract <T extends Animal> void copy(T couple);
    // при наличии пары

    public abstract void die();
    // умирать от голода или быть съеденными


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxCountInOneField() {
        return maxCountInOneField;
    }

    public void setMaxCountInOneField(int maxCountInOneField) {
        this.maxCountInOneField = maxCountInOneField;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getCountFoodMax() {
        return countFoodMax;
    }

    public void setCountFoodMax(double countFoodMax) {
        this.countFoodMax = countFoodMax;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}

