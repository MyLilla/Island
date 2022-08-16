package ua.com.shestakova.Island.animal.herbivore;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.Plant;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public abstract class Herbivore extends Animal {

    @Override
    public int eat(ArrayList<Animal> animals) {
        if (this.getSatiety() < 100) {
            int needCalories = 100 - this.getSatiety(); // сколько нужно еды

            Animal ani;
            try {
                ani = animals.stream()
                        .filter(Animal::getAlive)
                        .filter(x -> x.getWeight() <= needCalories)
                        .filter(x -> x instanceof Plant)
                        .findAny().get();
            } catch (NoSuchElementException e) {
                return -1; // если некого есть
            }
            return animals.indexOf(ani);
        }
        return -1;  // если сыт
    }
}