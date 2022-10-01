package ua.com.shestakova.island.creature;


public abstract class Herbivore extends Animal {

    protected Herbivore() {
        getPercent().put("Plant", 100);
    }

    @Override
    public boolean checkTypeAnimalForEat(Creature сreature) {
        return сreature instanceof Plant;
    }
}
