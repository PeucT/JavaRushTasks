package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;
        public CustomizedComparator(Comparator<T>... vararg){
            this.comparators = vararg;
        }

        @Override
        public int compare(Object o1, Object o2) {
            for (int i = 0; i < comparators.length; i++){
                int compareValue = comparators[i].compare((T) o1, (T) o2);
                if (compareValue != 0 ) { return compareValue; }
            }
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
