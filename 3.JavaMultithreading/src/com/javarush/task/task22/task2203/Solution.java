package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        int startIndex = string.indexOf("\t");
        //if (startIndex == -1) throw new TooShortStringException();
        int endIndex = string.indexOf("\t", startIndex + 1);
        if ((startIndex == -1) || ( endIndex == -1 )) throw new TooShortStringException();
        return string.substring(startIndex + 1, endIndex);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        //System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        System.out.println(getPartOfString(""));
    }
}
