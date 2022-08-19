package ua.com.shestakova.Island.animal.predator;

public class Fox extends Predator {

    public Fox() {
        setIcon("\uD83E\uDD8A");
        setWeight(8);
        setMaxCountTypeInLoc(30);
        setSpeed(2);
        setCountFoodMax(2);
        setLossSatiety(1);
        getPercent().put("Rabbit", 70);
    }
}
