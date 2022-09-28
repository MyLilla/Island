package ua.com.shestakova.island.performingActions;

import com.diogonunes.jcolor.Attribute;
import ua.com.shestakova.island.Dialog;
import ua.com.shestakova.island.exceptions.ThreadsException;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import static com.diogonunes.jcolor.Ansi.colorize;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static ua.com.shestakova.island.constructorGame.Tools.daysOfGame;

public class StartSimulation {
    private final LifeTime lifeTime = new LifeTime();


    public void startSimulation(PrintStream out) {

        out.println("To run the simulation, enter any text and click ENTER");
        Scanner scanner = new Scanner(System.in);
        scanner.next();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < daysOfGame; i++) {
            out.println("It is day number" + colorize(" " + (i + 1), Attribute.BRIGHT_RED_TEXT()));
            startActionsThreads(executorService);
            lifeTime.finalizeTact();
            Dialog.statistics.printMiniStatistics(out);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new ThreadsException("Sleep Exception in days tact" + e);
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(daysOfGame, MILLISECONDS);
        } catch (InterruptedException e) {
            throw new ThreadsException("Thread was interrupted " + e);
        }
    }

    private void startActionsThreads(ExecutorService executorService) {
        ReentrantLock reentrantLock = new ReentrantLock();

        executorService.submit(() -> {
            reentrantLock.lock();
            lifeTime.callActionCopy();
            reentrantLock.unlock();
        });
        executorService.submit(() -> {
            reentrantLock.lock();
            lifeTime.callActionEat();
            reentrantLock.unlock();
        });
        executorService.submit(() -> {
            reentrantLock.lock();
            lifeTime.callActionMove();
            reentrantLock.unlock();
        });
    }
}
