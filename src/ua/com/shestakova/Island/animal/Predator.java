package ua.com.shestakova.Island.animal;

public abstract class Predator extends Animal {
    public Predator() {
        getPercent().put("Plant", 0);
    }

    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return Herbivore.class.isAssignableFrom(animal.getClass());
    }
}
