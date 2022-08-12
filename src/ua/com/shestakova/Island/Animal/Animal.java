package ua.com.shestakova.Island.Animal;


public abstract class Animal {

    private int weight;
    private int countInThisFieldMax;
    private int speed;
    private double countFoodMax;
    private boolean canBeEating;
    private String icone;

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract <T> void eat(T food);
    // растения и/или других животных
    // проверка, есть ли подходящая еда

    public abstract void move(int line, int higt);
    // скорость

    public abstract <T extends Animal> void copy(T couple);
    // при наличии пары

    public abstract void die();
    // умирать от голода или быть съеденными


    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getIcone() {
        return icone;
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

    public boolean isCanBeEating() {
        return canBeEating;
    }

    public void setCanBeEating(boolean canBeEating) {
        this.canBeEating = canBeEating;
    }
}

