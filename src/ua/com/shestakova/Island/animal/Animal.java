package ua.com.shestakova.Island.animal;


import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ua.com.shestakova.Island.settings.Island.field;

public abstract class Animal {

    private int weight;
    private int maxCountInOneField;
    private int speed;
    private double countFoodMax;
    private boolean moved = false;
    private String icon;


    public abstract <T> void eat(T food);
    // растения и/или других животных
    // проверка, есть ли подходящая еда

    public void move(int x, int y){
        // определить, в какую из ячеек можно двигаться
        int xNew = x;
        int yNew = y;

        if (x < getSpeed()) {
            xNew = x + getSpeed();
        } else if (y < getSpeed()) {
            yNew = y + getSpeed();
        } else if (x > field.length || x >= getSpeed()) {
            xNew = x - getSpeed();
        } else if (y > field[x].length || y >= getSpeed()) {
            yNew = y - getSpeed();
        }
        // проверять лимит животных на следующе клетке
        // число животных этого вида в новой локации

        field[xNew][yNew].location.add(this);             // переселение
        field[x][y].location.remove(this);             // удаление со старой
        field[x][y].location.trimToSize();

        System.out.println(" на " + xNew + yNew);
        setMoved(true);
    }

    private int getCountThisTypeInNewLocation (int xNew, int yNew){

        int countThisAnimalInNewLocation = 0;
        for (Animal animal : field[xNew][yNew].location) {
            if (animal.getClass().equals(this.getClass()))
                countThisAnimalInNewLocation++;
        }
        return countThisAnimalInNewLocation;
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

