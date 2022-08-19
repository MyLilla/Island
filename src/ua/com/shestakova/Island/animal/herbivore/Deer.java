package ua.com.shestakova.Island.animal.herbivore;

public class Deer extends Herbivore{

    public Deer() {

        setIcon("\uD83E\uDD8C");
        setWeight(300);
        setMaxCountTypeInLoc(20);
        setSpeed(4);
        setCountFoodMax(50);
        setLossSatiety(10);
    }
}
