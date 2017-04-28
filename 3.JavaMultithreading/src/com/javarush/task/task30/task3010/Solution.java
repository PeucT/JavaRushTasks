package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        if (args == null || args.length == 0) { return; }
        String input = new String(args[0]);
        input = input.replaceAll("[0-9a-zA-Z]", "");
        if (input.length() != 0) { System.out.println("incorrect"); }
        else {
            input = new String(args[0]).toLowerCase();
            for (int i = 2; i <= 36; i++){
                try {
                    BigInteger bigI = new BigInteger(input, i);
                    System.out.println(i);
                    break;
                } catch (NumberFormatException e){
                }
            }
        }
    }
}