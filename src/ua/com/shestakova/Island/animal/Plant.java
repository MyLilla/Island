package ua.com.shestakova.Island.animal;

public class Plant extends Animal {
    public Plant() {
        setIcon("\uD83D\uDC0D");
        setMaxCountInOneField(15);
        setWeight(3);
    }

    @Override
    public <T> void eat(T food) {

    }

    @Override
    public void move(int x, int y) {
        // написать тут логику роста?
        this.setWeight(this.getWeight() + 1);
    }

    @Override
    public <T extends Animal> void copy(T couple) {

    }

    @Override
    public void die() {

    }
}
