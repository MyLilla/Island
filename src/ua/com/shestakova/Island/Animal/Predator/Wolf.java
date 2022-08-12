package ua.com.shestakova.Island.Animal.Predator;

import ua.com.shestakova.Island.Animal.Animal;
import ua.com.shestakova.Island.Animal.Herbivore.Herbivore;
import ua.com.shestakova.Island.FieldOfIsland.Location;

import static ua.com.shestakova.Island.FieldOfIsland.Island.fields;

public class Wolf extends Predator {

    public Wolf() {
        setWeight(50);
        setCountFoodMax(8);
        setSpeed(3);
        setCountInThisFieldMax(30);
        setIcone("\uD83D\uDC3A");
    }
    @Override
    public <T> void eat(T animal) {
        // с какой вероятностью животное съедает "пищу", если они находятся на одной клетке (50%)
         // многопоточный random.
        if (animal instanceof Herbivore) {
            System.out.println("I'm gonna eat you!");
        }
    }

    @Override
    public void move(int line, int higt) {
        if (line < getSpeed() || higt < getSpeed()) {
            int goLine = line + getSpeed();
        }
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
