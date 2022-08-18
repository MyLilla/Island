package ua.com.shestakova.Island.animal;

import lombok.Getter;
import lombok.Setter;
import ua.com.shestakova.Island.Date;
import ua.com.shestakova.Island.settings.Location;

import java.util.*;

import static ua.com.shestakova.Island.settings.Island.field;

@Setter
@Getter
public abstract class Animal {

    private String icon;
    private String name = this.getClass().getSimpleName();
    private int weight;
    private int maxCountTypeInLoc;
    private int speed;
    private int countFoodMax;
    private boolean moved = false;
    private int satiety = 0;
    private int lossEnergy;
    private boolean alive = true;
    private int chanceMakeCopy = 50;
    private Map<String, Integer> percent = new HashMap<>();


    public void eat(ArrayList<Animal> animals) {
        if (this.getSatiety() <= getCountFoodMax()) { // если голоден

            Animal ani;      // выбор животного
            try {
                ani = animals.stream()
                        .filter(Animal::isAlive)
                        .filter(this::checkTypeAnimalForEat) // должны получить класс, с чем сравнивать
                        .findAny().get();
            } catch (NoSuchElementException e) {  // если подходящих нет
                return;
            }
            if (checkFoodPercent(this, ani)) {     // вероятность съедения
                ani.setAlive(false);
                setSatiety(Math.min(getSatiety() + ani.getWeight(), getCountFoodMax()));    // + сытость (не переполнить)
                // сытость + вес съедаемого или полная энергия что меньшн
                System.out.print(ani.getIcon() + " будет съедена ");
                System.out.println();
                animals.remove(animals.indexOf(ani));
                animals.trimToSize();
            }
        }
    }

    public abstract boolean checkTypeAnimalForEat(Animal animal);

    private boolean checkFoodPercent(Animal hunter, Animal prey) {

        int random = new Random().nextInt(101); // вероятность в %

        for (String name : hunter.getPercent().keySet()) {  // проход по всем животным (key)
            if (name.equals(prey.getClass().getSimpleName())) {     // найти подходящего класса
                int probability = hunter.getPercent().get(name);   // взять его процент
                if (probability <= random) {     //  сравнить с выпавшим
                    return true;
                }
            }
        }
        return false;
    }

    public boolean move(int x, int y) {

        ArrayList<Animal> newLocation = getNewField(x, y);

        int count = getCountThisTypeInNewLocation(newLocation); // сколько животных такого типа на локации

        if (count < this.getMaxCountTypeInLoc()) {
            newLocation.add(this);             // переселение
            field[x][y].location.remove(this);             // удаление со старой
            field[x][y].location.trimToSize();
            setMoved(true);
            setSatiety(getSatiety() - getLossEnergy());
            return true;
        } else {
            return false;
        }
    }

    private int getCountThisTypeInNewLocation(ArrayList<Animal> loc) {

        int countThisAnimalInNewLocation = 0;
        for (Animal animal : loc) {
            if (animal.getClass().equals(this.getClass()))
                countThisAnimalInNewLocation++;
        }
        return countThisAnimalInNewLocation;
    }

    private ArrayList<Animal> getNewField(int x, int y) {
        int xNew = x;
        int yNew = y;
        if (x >= getSpeed() && y >= getSpeed() &&
                x < field.length - getSpeed() && y < field[x].length - getSpeed()) {
            int random = new Random().nextInt(4);
            switch (random) {
                case 0 -> xNew = down(x);
                case 1 -> yNew = right(y);
                case 2 -> xNew = up(x);
                case 3 -> yNew = left(y);
            }
        } else if (x < getSpeed()) {
            xNew = down(x);
        } else if (y < getSpeed()) {
            yNew = left(y);
        } else if (x >= field.length - getSpeed()) {
            xNew = up(x);
        } else if (y >= field[x].length - getSpeed()) {
            yNew = right(y);
        }
        //System.out.println(" в " + xNew + yNew);
        return field[xNew][yNew].location;
    }

    private int down(int x) {
        return x + this.getSpeed();
    }

    private int up(int x) {
        return x - this.getSpeed();
    }

    private int left(int y) {
        return y + this.getSpeed();
    }

    private int right(int y) {
        return y - this.getSpeed();
    }

    public void copy(ArrayList<Animal> animals) {

        int chanceRandom = new Random().nextInt(100);

        if (getChanceMakeCopy() < chanceRandom) {
      // System.out.println(this.getName() + " ищет пару");

            int countTypeInLoc = Location.getCountTypeInLoc(animals, this);
            if (countTypeInLoc < getMaxCountTypeInLoc() &&
                countTypeInLoc > 1) {                       // если число животных больше 0 и меньше максимума

            Animal newAni;
            for (Map.Entry entry : Date.mapAllAnimals.entrySet()) {
                if (entry.getValue().getClass() == (this).getClass()) {
                    newAni = Location.createNewAnimal((int) entry.getKey());
                   // System.out.println("пара создана" + newAni.getName());
                    animals.add(newAni);
                    break;
                }
            }
        }
        }
    }

    public void die(int x, int y) {
        if (!isAlive() || getSatiety() <= 0) {
            field[x][y].location.remove(this);             // удаление со старой
            field[x][y].location.trimToSize();
            setAlive(false);
        }
    }
}

