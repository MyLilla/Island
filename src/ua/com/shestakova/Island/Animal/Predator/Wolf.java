package ua.com.shestakova.Island.Animal.Predator;

import ua.com.shestakova.Island.Animal.Animal;
import ua.com.shestakova.Island.Animal.Herbivore.Herbivore;

public class Wolf extends Predator {

    public Wolf() {
        setWeight(50);
        setCountFoodMax(8);
        setSpeed(3);
        setCountInThisFieldMax(30);
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
    public void move(Animal animal, int steps) {
        if (getSpeed() < steps) System.out.println("You cant to make so much steps");
        //
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
