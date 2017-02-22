package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/*
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        //String fileName = "D:\\JavaProjects\\IO\\input.txt";
        if (fileName == null) System.exit(1);
        reader.close();

        getWordsList(fileName);


        for (int i = 0; i < list.size(); i ++) {
            String word = list.get(i);
            String reversedWord = new StringBuilder(word).reverse().toString();
            list.remove(i);
            i--;
            if (list.contains(reversedWord)) {
                Pair pair = new Pair();
                pair.first = reversedWord;
                pair.second = word;
                result.add(pair);
                list.remove(reversedWord);
            }
        }

        for (Pair entry : result) {
            System.out.println(entry.toString());
        }

    }

    private static void getWordsList(String fileName) throws IOException {

        BufferedReader reader1 = new BufferedReader(new FileReader(new File(fileName)));
        String wholeFile = "";
        boolean initialLine = true;
        while (true) {
            String line = reader1.readLine();
            if (line == null) break;
            line = line.replaceAll("\\p{Punct}", "");
            if (initialLine) {
                initialLine = false;
                wholeFile = line;
            } else {
                wholeFile = wholeFile + " " + line;
            }
        }
        reader1.close();
        wholeFile = wholeFile.replaceAll("  +", " ");
        String[] words = wholeFile.split(" ");
        for (String entry : words) {
            list.add(entry);
        }
    }

    public static String reverseWord(String word){
        StringBuilder strB = new StringBuilder(word);
        return strB.reverse().toString();
    }

    public static class Pair {
        String first;
        String second;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
