package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        boolean isAddedLow = false;
        boolean isAddedUpper = false;
        boolean isAddedNumber = false;
        char lowerCaseChar = alphabet.charAt ((int) (Math.random() * 25));
        char upperCaseChar = alphabet.charAt (26 + (int) (Math.random() * 25));
        char number = alphabet.charAt (52 + (int) (Math.random() * 9));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < 8; i++){
            if (!isAddedLow) {
                out.write((byte)lowerCaseChar);
                isAddedLow = true;
            }else if (!isAddedNumber) {
                out.write((byte) number);
                isAddedNumber = true;
            }else if (!isAddedUpper) {
                out.write((byte) upperCaseChar);
                isAddedUpper = true;
            }else {
                out.write((byte) alphabet.charAt((int) (Math.random() * alphabet.length()) ));
            }
        }
        return out;
    }
}