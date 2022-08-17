package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.Deer;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;

import java.util.*;

public abstract class Predator extends Animal {
    public Predator() {
        getPercent().put("Plant", 0);
    }

    @Override
    public int eat(ArrayList<Animal> animals) {

        if (this.getSatiety() < FULL_ENERGY) { // если голоден

            int needCalories = FULL_ENERGY - this.getSatiety(); // сколько нужно еды
            Animal ani;      // выбор животного
            try {
                ani = animals.stream()
                        .filter(Animal::getAlive)
                       // .filter(x -> x.getWeight() <= needCalories)
                        .filter(x -> x instanceof Herbivore)
                        .findAny().get();
            } catch (NoSuchElementException e) {  // если подходящих нет
                return -1;
            }
            if (checkFoodPercent(this, ani)) {                       // вероятность съедения
                ani.setAlive(false);
                this.setSatiety(this.getSatiety() + ani.getWeight());             // - сытость
                return animals.indexOf(ani);
            }
        }
        return -1;  // если сыт
    }

    private boolean checkFoodPercent (Animal hunter, Animal prey) {

        int random = new Random().nextInt(101); // вероятность в %

        for (String name : hunter.getPercent().keySet()) {  // проход по всем животным (key)
            if (name.equals(prey.getClass().getSimpleName())) {     // найти подходящего класса
                int probability = hunter.getPercent().get(name);   // взять его процент
                if (probability <= random) {     //  сравнить с выпавшим
                    return true;
                }
            }
        }
        return false;
    }
}
