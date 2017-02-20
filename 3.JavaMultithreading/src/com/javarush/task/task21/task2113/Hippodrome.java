package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArchMage on 20.02.17.
 */
public class Hippodrome {
    private List<Horse> horses; /*= new ArrayList<Horse>();*/

    public static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse: getHorses()) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse: getHorses()) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        for (Horse horse : getHorses()){
            if (winner != null ? horse.getDistance() > winner.getDistance() : true) {
                    winner = horse;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println(String.format("Winner is %s!", getWinner().getName()));
    }

    public static void main (String[] args) throws InterruptedException {
        game = new Hippodrome(new ArrayList<Horse>());
        Horse horse1 = new Horse("Lambo",3d,0d);
        Horse horse2 = new Horse("Jambo",3d,0d);
        Horse horse3 = new Horse("Crambo",3d,0d);
        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);
        game.run();
        game.printWinner();
    }
}
