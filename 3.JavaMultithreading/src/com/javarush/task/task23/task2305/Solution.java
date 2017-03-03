package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] result = new Solution[2];
        for (int i = 0; i < 2; i++) {
            Solution sol = new Solution();
            sol.innerClasses[0] = sol.new InnerClass();
            sol.innerClasses[1] = sol.new InnerClass();
            result[i] = sol;
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
