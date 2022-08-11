package ua.com.shestakova.Island.Animal;

public abstract class Animal {

    private int weight;
    private int countInThisFieldMax;
    private int speed;
    private double countFoodMax;
    private boolean canBeEating;

    public Animal() {
    }


    public abstract <T> void eat(T food);
    // растения и/или других животных
    // проверка, есть ли подходящая еда

    public abstract <T extends Animal> void move(T animal, int steps);
    // скорость


    public abstract <T extends Animal> void copy(T couple);
    // при наличии пары

    public abstract void die();

    public int getWeight() {
        return this.weight;
    }

    public int getCountInThisFieldMax() {
        return this.countInThisFieldMax;
    }

    public int getSpeed() {
        return this.speed;
    }

    public double getCountFoodMax() {
        return this.countFoodMax;
    }

    public boolean isCanBeEating() {
        return this.canBeEating;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCountInThisFieldMax(int countInThisFieldMax) {
        this.countInThisFieldMax = countInThisFieldMax;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCountFoodMax(double countFoodMax) {
        this.countFoodMax = countFoodMax;
    }

    public void setCanBeEating(boolean canBeEating) {
        this.canBeEating = canBeEating;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Animal)) return false;
        final Animal other = (Animal) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getWeight() != other.getWeight()) return false;
        if (this.getCountInThisFieldMax() != other.getCountInThisFieldMax()) return false;
        if (this.getSpeed() != other.getSpeed()) return false;
        if (Double.compare(this.getCountFoodMax(), other.getCountFoodMax()) != 0) return false;
        if (this.isCanBeEating() != other.isCanBeEating()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Animal;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getWeight();
        result = result * PRIME + this.getCountInThisFieldMax();
        result = result * PRIME + this.getSpeed();
        final long $countFoodMax = Double.doubleToLongBits(this.getCountFoodMax());
        result = result * PRIME + (int) ($countFoodMax >>> 32 ^ $countFoodMax);
        result = result * PRIME + (this.isCanBeEating() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Animal(weight=" + this.getWeight() + ", countInThisFieldMax=" + this.getCountInThisFieldMax() + ", speed=" + this.getSpeed() + ", countFoodMax=" + this.getCountFoodMax() + ", canBeEating=" + this.isCanBeEating() + ")";
    }
    // умирать от голода или быть съеденными

}

