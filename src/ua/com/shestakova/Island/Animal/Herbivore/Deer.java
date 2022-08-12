package ua.com.shestakova.Island.Animal.Herbivore;

import ua.com.shestakova.Island.Animal.Animal;

public class Deer extends Herbivore{

    public Deer() {
        setIcone("\uD83E\uDD8C");
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
