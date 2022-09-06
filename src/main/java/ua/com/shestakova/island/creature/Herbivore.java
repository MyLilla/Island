package ua.com.shestakova.island.creature;


public abstract class Herbivore extends Animal {

    public Herbivore() {
        getPercent().put("Plant", 100);
    }

    @Override
    public boolean checkTypeAnimalForEat(Сreature сreature) {
        return сreature instanceof Plant;
    }
}
