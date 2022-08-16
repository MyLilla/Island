package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;

public class Wolf extends Predator {

    public Wolf() {
        setWeight(50);
        setCountFoodMax(8);
        setSpeed(3);
        setMaxCountInOneField(2);
        setIcon("\uD83D\uDC3A");
        setSatiety(50);
    }


    @Override
    public <T extends Animal> void copy(T couple) {
        if (couple instanceof Wolf) {
            System.out.println("I'm ready make my copy!");
        }
    }

    @Override
    public void die() {

    }

}
