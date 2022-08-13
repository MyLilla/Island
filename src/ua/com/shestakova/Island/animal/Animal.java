package ua.com.shestakova.Island.animal;


import static ua.com.shestakova.Island.settings.Island.field;

public abstract class Animal {

    private int weight;
    private int countInThisFieldMax;
    private int speed;
    private double countFoodMax;
    private boolean moved = false;
    private String icon;


    public abstract <T> void eat(T food);
    // растения и/или других животных
    // проверка, есть ли подходящая еда

    public void move(int x, int y){
        // определить, в какую из ячеек можно двигаться
        // если координаты у края - двигаться внутрь

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

        field[xNew][yNew].location.add(this);             // переселение
        field[x][y].location.remove(this);             // удаление со старой
        field[x][y].location.trimToSize();

        System.out.println(" на " + xNew + yNew);
        setMoved(true);
    }
    // скорость

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

    public int getCountInThisFieldMax() {
        return countInThisFieldMax;
    }

    public void setCountInThisFieldMax(int countInThisFieldMax) {
        this.countInThisFieldMax = countInThisFieldMax;
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

