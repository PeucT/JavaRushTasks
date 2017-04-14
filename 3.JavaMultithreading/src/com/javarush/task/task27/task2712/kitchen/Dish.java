package com.javarush.task.task27.task2712.kitchen;

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
