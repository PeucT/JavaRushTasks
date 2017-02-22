package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        String[] arr = getTokens("delimiter", "i");
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer token = new StringTokenizer(query, delimiter);
        ArrayList<String> list = new ArrayList<>();
        while (token.hasMoreTokens()) {
             list.add(token.nextToken());
        }
        return list.toArray(new String[list.size()]);
    }
}
