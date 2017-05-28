package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder builder = new StringBuilder();
        int symbol = 0;
        if (reader != null) {
            while ((symbol = reader.read()) != -1) {
                builder.append((char) (symbol + key));
            }
        }
        return builder.toString();
    }

}
