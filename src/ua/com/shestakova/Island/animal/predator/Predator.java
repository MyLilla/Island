package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public abstract class Predator extends Animal {

    @Override
    public int eat(ArrayList<Animal> animals) {

        if (this.getSatiety() < 100) {
            int needCalories = 100 - this.getSatiety(); // сколько нужно еды
//            for (Animal animal : animals) {
//                if (animal.getAlive() && animal.getWeight() <= needCalories
//                        && animal instanceof Herbivore) {
//                    return animals.indexOf(animal);
//                }
//             }

            Animal ani;
            try {
                ani = animals.stream()
                        .filter(Animal::getAlive)
                        .filter(x -> x.getWeight() <= needCalories)
                        .filter(x -> x instanceof Herbivore)
                        .findAny().get();
            } catch (NoSuchElementException e) {
                return -1;
            }
            return animals.indexOf(ani);
        }
        return -1;  // если сыт

    }
}
