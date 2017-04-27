package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        //напишите тут ваш код
        if (s.length() > 1 && "0".equals(String.valueOf(s.charAt(0)))) {
                if ("x".equals(String.valueOf(s.charAt(1)))) {
                    s = String.valueOf(Integer.parseInt(s.substring(2), 16));
                }else if ("b".equals(String.valueOf(s.charAt(1)))){
                    s = String.valueOf(Integer.parseInt(s.substring(2), 2));
                }else {
                    s = String.valueOf(Integer.parseInt(s, 8));
                }
        }

        return s;
    }
}
