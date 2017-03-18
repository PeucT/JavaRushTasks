package com.javarush.task.task26.task2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        /*Integer[] array = {13, 8, 15, 5, 17, 14};
        array = sort(array);
        for (Integer entry: array){
            System.out.println(entry);
        }*/
    }

    public static Integer[] sort(Integer[] array) {
        // Находим медианное значение
        Arrays.sort(array);
        final double mediana;
        if ((array.length) % 2 == 0) {
            int index = ( array.length / 2 ) - 1;
            mediana = (array[index] + array[index + 1]) / 2d;
        } else {
            mediana = array[(array.length - 1) / 2];
        }

        // Сортируем массив по удаленности от медианного значения
        Arrays.sort(array, new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                if (o1.equals(o2)) return 0;
                if ((Math.abs(Double.parseDouble(o1.toString()) - mediana) - Math.abs(Double.parseDouble(o2.toString()) - mediana)) > 0 ) { return 1; }
                else if ((Math.abs(Double.parseDouble(o1.toString()) - mediana) - Math.abs(Double.parseDouble(o2.toString()) - mediana)) < 0 ) { return -1; }
                else return 0;
            }
        });

        return array;
    }
}
