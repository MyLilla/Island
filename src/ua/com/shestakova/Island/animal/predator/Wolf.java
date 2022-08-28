package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Predator;

public class Wolf extends Predator {
    public Wolf() {

        setIcon("\uD83D\uDC3A");
        setWeight(50);
        setMaxCountTypeInLoc(30);
        setSpeed(3);
        setCountFoodMax(8);
        setLossSatiety(3);
        getPercent().put("Horse", 10);
        getPercent().put("Rabbit", 60);
        getPercent().put("Deer", 15);
        getPercent().put("Mouse", 80);
        getPercent().put("Goat", 60);
        getPercent().put("Sheep", 70);
        getPercent().put("Boar", 15);
        getPercent().put("Buffalo", 10);
        getPercent().put("Duck", 40);
    }
}
