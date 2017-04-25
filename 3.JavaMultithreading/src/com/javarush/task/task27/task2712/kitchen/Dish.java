package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by ArchMage on 14.04.17.
 */
public enum Dish {
    //Fish(11),
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int i) {
        duration = i;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString(){
        /*if (values().length == 0) {
            return "";
        }
        return Arrays.toString(values()).substring(1, Arrays.toString(values()).length() - 1);*/

        StringBuilder strB = new StringBuilder();
        boolean isFirst = true;
        for (Dish entry : Dish.values()){
            if (!isFirst) {
                strB.append(", ");
            } else { isFirst = false; }
            strB.append(entry.toString());
        }
        return strB.toString();
    }
}
