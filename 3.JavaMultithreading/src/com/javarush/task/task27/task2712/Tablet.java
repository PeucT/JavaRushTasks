package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;


import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ArchMage on 14.04.17.
 */
public class Tablet extends Observable {
    protected final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private Observer observer;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){

        try {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            setChanged();
            notifyObservers(order);
            return order;
        }
        catch (IOException e){
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }


    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }


}
