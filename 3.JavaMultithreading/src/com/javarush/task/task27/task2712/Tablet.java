package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
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
            if (!order.isEmpty()) {
                ConsoleHelper.writeMessage(order.toString());
                setChanged();
                notifyObservers(order);

                // Часть 8
                // 5. Чтобы тестировать данную функциональность, нужно добавить вызов processVideos метода у AdvertisementManager.
                // Очевидно, что этот метод должен вызываться во время создания заказа, а точнее — в параллельном режиме.
                // Заказ готовится в то время, как видео смотрится.
                // Добавьте вызов метода processVideos() в нужное место.

                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            }
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
