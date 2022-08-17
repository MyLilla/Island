package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.Deer;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;

import java.util.*;

import static ua.com.shestakova.Island.settings.Island.field;

public abstract class Predator extends Animal {
    public Predator() {
        getPercent().put("Plant", 0);
    }

    @Override
    public void eat(ArrayList<Animal> animals) {

        int result = -1;

        if (this.getSatiety() < FULL_ENERGY) { // если голоден

            Animal ani;      // выбор животного
            try {
                ani = animals.stream()
                        .filter(Animal::getAlive)
                        .filter(x -> x instanceof Herbivore)
                        .findAny().get();
            } catch (NoSuchElementException e) {  // если подходящих нет
                return;
            }
            if (checkFoodPercent(this, ani)) {     // вероятность съедения
                ani.setAlive(false);
                setSatiety(getSatiety() + ani.getWeight() > FULL_ENERGY ? FULL_ENERGY
                        : getSatiety() + ani.getWeight());          // + сытость (не переполнить)
                    System.out.print(ani.getIcon() + " будет съедена ");
                    System.out.println();
                    animals.remove(animals.indexOf(ani));
                    animals.trimToSize();
            }
        }
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
