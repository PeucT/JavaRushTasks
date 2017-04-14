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
        StringBuilder strB = new StringBuilder();
        if (dishes == null || dishes.size() == 0) { return ""; }
        else {
            strB.append("Your order: [");
            boolean isFirst = true;
            for (int i = 0; i < dishes.size(); i++){
                if (isFirst) {
                    isFirst = false;
                } else {
                    strB.append(", ");
                }
                strB.append(dishes.get(i));

            }
            strB.append("] of ");
            strB.append(tablet.toString());
        }
        return strB.toString();
    }
}
