package ua.com.shestakova.island.creature;

public abstract class Predator extends Animal {
    public Predator() {
        getPercent().put("Plant", 0);
    }

    @Override
    public boolean checkTypeAnimalForEat(Сreature animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass());
    }
}
