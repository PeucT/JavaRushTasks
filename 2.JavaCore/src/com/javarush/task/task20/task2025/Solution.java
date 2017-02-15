package com.javarush.task.task20.task2025;

import java.util.*;

/*
Алгоритмы-числа
*/
public class Solution {

    private static int[][] matrix;

    private static void fillMatrix() {
        int n = 11;
        matrix = new int[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++){
                int value = 1;
                for (int k = 0; k < j; k++){
                    value = value * i;
                }
                matrix[i][j] = value;
            }
        }
    }

    public static long[] getNumbers(long N) {
        long[] result = null;

        fillMatrix();
        TreeSet<Long> list = new TreeSet<Long>();

        for (long i = 1; i <= N; i++) {
            int power = 0;
            long tempNumber = i;
            boolean isNotChecked = false;
            int prevChar = 0;
            while (true) {
                int lastChar = (int) (tempNumber % 10);
                tempNumber = tempNumber / 10;
                power++;
                if ( tempNumber == 0 ) break;
                if (power > 1 && lastChar < prevChar) {
                    isNotChecked = true;
                    break;
                }
                prevChar = lastChar;
            }

            /*power = getPower(i);
            if (getPoweredSumm(i,power) == i) {
                list.add(i);

            }*/

            if (!isNotChecked) {
                long sum1 = getPoweredSumm(i, power);
                if (getPower(sum1) == power) {
                    if (sum1 == getPoweredSumm(sum1, power)) {
                        list.add(sum1);
                    }
                }
            }




        }

        if (!list.isEmpty()) {
            result = new long[list.size()];
            int count = 0;
            for (long entry: list) {
                result[count] = entry;
                count++;
            }
            return result;
            //return list.toArray(new Long[list.size() - 1]);
        } else return new long[1];

    }

    private static int getPower(long number) {
        int power = 0;
        while (true) {
            number = number / 10;
            power++;
            if (number == 0) break;
        }
        return power;
    }

    private static long getPoweredSumm(long number, int power) {
        long sum = 0;
        for (int j = 0; j < power; j++) {
            int symbol = (int) (number % 10);
            //sum = (long) (sum + Math.pow(symbol, power));
            sum = sum + matrix[symbol][power];
            number = number / 10;
            //sum = (long) (sum + Math.pow(Double.parseDouble(String.valueOf(number.charAt(j))), Double.valueOf(String.valueOf(power))));
        }
        return sum;
    }


    public static void main(String[] args) {
        //long N = Long.MAX_VALUE;
        long baseN =    1000000;
        long N =        100000000;
        //long N =        146511208;

        long memoryVolumeStart = Runtime.getRuntime().freeMemory();
        long startTime = new Date().getTime();
        //Long[] result = getNumbersBaseAlg(baseN);
        //Long[] result = getNumbers(N);
        long[] result = getNumbers(N);
        long endTime = new Date().getTime();
        long memoryVolumeEnd = Runtime.getRuntime().freeMemory();
        for (int i = 0; i < result.length ; i++) {
            System.out.println(result[i]);
        }
        System.out.println("Memory consumption: " + ( ( memoryVolumeStart - memoryVolumeEnd ) / 1024 / 1024) + " Mb");
        System.out.println("Time consumption: " + (( endTime - startTime ) / 1000) + " s");
    }
}
