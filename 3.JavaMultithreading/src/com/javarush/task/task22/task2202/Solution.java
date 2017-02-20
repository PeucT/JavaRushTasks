package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        String result = "";
        int startIndex = 0;
        int endSpaceIndex = 0;
        try {
            result = "";
            startIndex = string.indexOf(" ") + 1;
            endSpaceIndex = startIndex - 1;
            for (int i = 0; i < 3; i++) {
                endSpaceIndex = string.indexOf(" ", endSpaceIndex + 1);
                if ((i < 3) && endSpaceIndex == -1) throw new TooShortStringException(new Exception());
            }
            if (string.indexOf(" ", endSpaceIndex + 1) != -1) {
                endSpaceIndex = string.indexOf(" ", endSpaceIndex + 1);
            } else {
                endSpaceIndex = string.length();
            }
        } catch (Exception e) {
            throw new TooShortStringException(e);
        }

        return string.substring(startIndex, endSpaceIndex);
    }

    public static class TooShortStringException extends RuntimeException {
        public TooShortStringException (Throwable e){
            super(e);
        }
    }
}
