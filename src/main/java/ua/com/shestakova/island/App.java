package ua.com.shestakova.island;

import ua.com.shestakova.island.creature.Creature;
import ua.com.shestakova.island.creature.predator.Wolf;
import ua.com.shestakova.island.performingActions.Simulation;

import static java.lang.System.out;

public class App {

    public static void main(String[] args) {

        Simulation simulation = new Simulation();
        simulation.startSimulation(out);

    }
}
