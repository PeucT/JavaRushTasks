package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArchMage on 14.04.17.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        writeMessage(Dish.allDishesToString());
        writeMessage("Выберите блюда");
        List<Dish> list = new ArrayList<>();
        String input = null;
        while (!"exit".equals(input = reader.readLine())) {
            try{
                Dish inputDish = Dish.valueOf(input);
                list.add(inputDish);
            } catch (IllegalArgumentException e){
                writeMessage("Блюдо отсутствует в меню");
            }

        }
        return list;
    }

}
