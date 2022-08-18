package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;
import ua.com.shestakova.Island.animal.herbivore.Deer;
import ua.com.shestakova.Island.animal.herbivore.Herbivore;

public abstract class Predator extends Animal {
    public Predator() {
        getPercent().put("Plant", 0);
    }

    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        // true наследуется ли олень от травки
        return Herbivore.class.isAssignableFrom(animal.getClass());
    }
}
