package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by ArchMage on 14.04.17.
 */
public enum Dish {
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

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
