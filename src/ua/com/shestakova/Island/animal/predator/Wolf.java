package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;
import ua.com.shestakova.Island.settings.Location;

import static ua.com.shestakova.Island.settings.Island.field;

public class Wolf extends Predator {

    public Wolf() {
        setWeight(50);
        setCountFoodMax(8);
        setSpeed(3);
        setCountInThisFieldMax(30);
        setIcon("\uD83D\uDC3A");
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
    public <T extends Animal> void copy(T couple) {
        if (couple instanceof Wolf) {
            System.out.println("I'm ready make my copy!");
        }
    }

    @Override
    public void die() {

    }

}
