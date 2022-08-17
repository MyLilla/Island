package ua.com.shestakova.Island.animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static ua.com.shestakova.Island.settings.Island.field;

public abstract class Animal {

    public static int FULL_ENERGY = 100;
    private String icon;
    private int weight;
    private int MAX_COUNT_OF_THIS_ANIMAL;
    private int speed;
    private double countFoodMax;
    private boolean moved = false;
    private int satiety = 100;
    private boolean alive = true;
    private int lossEnergy;
    private Map<String, Integer> percent = new HashMap<>();

    public Map<String, Integer> getPercent() {
        return percent;
    }
    public void setPercent(Map<String, Integer> percent) {
        this.percent = percent;
    }

    public int getLossEnergy() {
        return lossEnergy;
    }

    public void setLossEnergy(int lossEnergy) {
        this.lossEnergy = lossEnergy;
    }

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

    public abstract void eat(ArrayList <Animal> animals);

    public boolean move(int x, int y) {

        ArrayList<Animal> newLocation = getNewField(x, y);

        int count = getCountThisTypeInNewLocation(newLocation); // сколько животных такого типа на локации

        if (count < this.getMAX_COUNT_OF_THIS_ANIMAL()) {
            newLocation.add(this);             // переселение
            field[x][y].location.remove(this);             // удаление со старой
            field[x][y].location.trimToSize();
            setMoved(true);
            setSatiety(getSatiety() - getLossEnergy());
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
        System.out.println(" в " + xNew + yNew);
        return field[xNew][yNew].location;
    }

    private int down (int x){
        return x + this.getSpeed();
    }
    private int up (int x){
        return x - this.getSpeed();
    }
    private int left (int y){
        return y + this.getSpeed();
    }
    private int right (int y){
        return y - this.getSpeed();
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

    public int getMAX_COUNT_OF_THIS_ANIMAL() {
        return MAX_COUNT_OF_THIS_ANIMAL;
    }

    public void setMAX_COUNT_OF_THIS_ANIMAL(int MAX_COUNT_OF_THIS_ANIMAL) {
        this.MAX_COUNT_OF_THIS_ANIMAL = MAX_COUNT_OF_THIS_ANIMAL;
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

