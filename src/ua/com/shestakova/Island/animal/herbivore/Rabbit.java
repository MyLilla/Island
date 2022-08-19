package ua.com.shestakova.Island.animal.herbivore;

public class Rabbit extends Herbivore {

    public Rabbit() {
        setIcon("\uD83D\uDC07");
        setWeight(2);
        setMaxCountTypeInLoc(150);
        setSpeed(2);
        setCountFoodMax(5); //
        setLossSatiety(1);
    }
}
