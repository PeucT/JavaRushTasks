package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by ArchMage on 14.04.17.
 */
public class Order {
    protected List<Dish> dishes;
    private final Tablet tablet;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) return "";
        else return "Your order: " + dishes + " of " + tablet;
    }

    public int getTotalCookingTime(){
        int totalDureation = 0;
        for (Dish entry : dishes){
            totalDureation += entry.getDuration();
        }
        return totalDureation;
    }

    public boolean isEmpty(){
        return (dishes == null || dishes.size() == 0);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

}
