package com.javarush.task.task30.task3001;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/*
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumerationSystemType._16, "6df");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
        System.out.println(result);    //expected 3337

        /*Number number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._3);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._4);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._5);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._6);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._7);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._9);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._10);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._12);
        System.out.println(result);
        number = new Number(NumerationSystemType._2, "1101001000000001100001001110110111111100110010101000100111011011011001001011001100011001100000111101111");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._16);
        System.out.println(result);*/
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {

        BigInteger tempNumber = new BigInteger(number.getDigit(), number.getNumerationSystem().getNumerationSystemIntValue());

        return new Number(expectedNumerationSystem, tempNumber.toString(expectedNumerationSystem.getNumerationSystemIntValue()));

        // Собственная реализация для небольших чисел

        /*String digits = number.getDigit().toLowerCase();
        int sourceNumSystem = number.getNumerationSystem().getNumerationSystemIntValue();
        Alphabet alphabet = new Alphabet();
        Map<Character, Integer> map = alphabet.getMap();
        int power = 1;
        int decimalNumber = 0;
        boolean isMinus = false;
        for (int i = digits.length() - 1; i >= 0; i--){
            if (i == 0 && "-".equals(String.valueOf(digits.charAt(i)))) {
                isMinus = true;
                break;
            }
            Integer value = map.get(digits.charAt(i));
            //int value = map.get(digits.charAt(i));
            if (value == null ||  value >= sourceNumSystem) { throw new NumberFormatException(); }
            if (power != 0) {
                decimalNumber += value * power;
            } else { decimalNumber += value; }
            power *= sourceNumSystem;
        }

        StringBuilder convertedNumberDigits = new StringBuilder();
        while (decimalNumber != 0) {
            int digitValue = decimalNumber % expectedNumerationSystem.getNumerationSystemIntValue();
            convertedNumberDigits.append(alphabet.getByValue(digitValue));
            decimalNumber /= expectedNumerationSystem.getNumerationSystemIntValue();
        }
        if (isMinus) { convertedNumberDigits.append("-"); }
        convertedNumberDigits = convertedNumberDigits.reverse();
        return new Number(expectedNumerationSystem, convertedNumberDigits.toString());*/
    }

    // Собственная реализация для небольших чисел
    /*private static class Alphabet{
        private Map<Character, Integer> map = new HashMap<>();

        public Alphabet(){
            map.put('0', 0);
            map.put('1', 1);
            map.put('2', 2);
            map.put('3', 3);
            map.put('4', 4);
            map.put('5', 5);
            map.put('6', 6);
            map.put('7', 7);
            map.put('8', 8);
            map.put('9', 9);
            map.put('a', 10);
            map.put('b', 11);
            map.put('c', 12);
            map.put('d', 13);
            map.put('e', 14);
            map.put('f', 15);
        }

        public Map<Character, Integer> getMap() {
            return map;
        }

        public Character getByValue(int value){
            for (Map.Entry<Character, Integer> entry : map.entrySet()){
                if (entry.getValue().equals(value)) { return entry.getKey(); }
            }
            return null;
        }
    }*/
}
