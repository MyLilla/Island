package ua.com.shestakova.Island.Animal;

public class Plant extends Animal {
    public Plant() {
        setIcone("\uD83D\uDC0D");
    }

    @Override
    public <T> void eat(T food) {

    }

    @Override
    public void move(int line, int higt) {
    }

    @Override
    public <T extends Animal> void copy(T couple) {

    }

    @Override
    public void die() {

    }
}
