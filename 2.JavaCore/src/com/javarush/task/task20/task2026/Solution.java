package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        byte[][] temp;
        temp = a.clone();
        for (int i = 0; i < temp.length; i++){
            for (int j = 0; j < temp.length - 1; j++){
                if (temp[i][j] == 1 && temp[i][j+1] == 1) {
                    temp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < temp.length - 1; i++){
            for (int j = 0; j < temp.length; j++){
                if (temp[i][j] == 1 && temp[i+1][j] == 1) {
                    temp[i][j] = 0;
                }
            }
        }

        int summ = 0;
        for (int i = 0; i < temp.length; i++){
            for (int j = 0; j < temp.length; j++){
                if (temp[i][j] == 1) {
                    summ++;
                }
            }
        }
        return summ;
    }
}
