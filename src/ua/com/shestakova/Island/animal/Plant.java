package ua.com.shestakova.Island.animal;

public class Plant extends Animal {
    public Plant() {
        setIcon("\uD83D\uDC0D");
    }

    @Override
    public <T> void eat(T food) {

    }

    @Override
    public void move(int x, int y) {
    }

    @Override
    public <T extends Animal> void copy(T couple) {

    }

    @Override
    public void die() {

    }
}
