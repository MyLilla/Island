package ua.com.shestakova.Island.animal.predator;

import ua.com.shestakova.Island.animal.Animal;

public class Wolf extends Predator {


    public Wolf() {

        setIcon("\uD83D\uDC3A");
        setWeight(50);
        setMaxCountTypeInLoc(30);
        setSpeed(3);
        setCountFoodMax(8);
        setLossEnergy(2);
        getPercent().put("Deer", 15);
        getPercent().put("Rabbit", 60);
    }





}
