package ua.com.shestakova.Island.animal;


public abstract class Herbivore extends Animal {

    public Herbivore() {
        getPercent().put("Plant", 100);
    }
    @Override
    public boolean checkTypeAnimalForEat(Animal animal) {
        return animal instanceof Plant;
    }
}