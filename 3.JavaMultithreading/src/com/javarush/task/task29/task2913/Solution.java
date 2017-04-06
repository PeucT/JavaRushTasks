package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;


    public static String getAllNumbersBetween(int a, int b){
        String result = null;
        for (int i = 0; i <=  Math.abs(a - b); i++) {
            if (a > b) {
                if (i == 0) {
                    result = "" + a;
                } else {
                    result = result + " " + (a - i);
                }
            } else {
                if (i == 0) {
                    result = "" + a;
                } else {
                    result = result + " " + (a + i);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}