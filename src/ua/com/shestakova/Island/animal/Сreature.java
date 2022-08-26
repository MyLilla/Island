package ua.com.shestakova.Island.animal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@ToString
public abstract class Сreature {

    private String icon;
    private String name = this.getClass().getSimpleName();;
    private double weight;
    private int maxCountTypeInLoc;
    private boolean alive = true;
    private int chanceMakeCopy = 70;

    public abstract boolean checkTypeAnimalForEat(Сreature animal);
    public abstract void copy(ArrayList<Сreature> creatures, int contTypeInLocation);

}

