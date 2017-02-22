package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String fileName = reader.readLine();
        String fileName = "D:\\JavaProjects\\IO\\input.txt";
        if (fileName == null) System.exit(1);
        reader.close();
        Path path = Paths.get(fileName);
        List<String> lines =  Files.readAllLines(path, Charset.forName("utf-8"));
        String[] words;
        if (!lines.isEmpty()) {
            words = lines.get(0).split(" ");
        } else words = null;
        StringBuilder result = getLine(words);

        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words == null) return new StringBuilder();

        ArrayList<String> listNew = new ArrayList<>(Arrays.asList(words));
        ArrayList<String> resultList = new ArrayList<>();

        StringBuilder result = new StringBuilder();
        String letter = "";
        boolean isFirst = true;
        int length = listNew.size();
        boolean escaped = true;
        int counter = 0;
        boolean mainCicleFirst = true;
        while (true){
            if (mainCicleFirst) {
                mainCicleFirst = false;
            } else {
                String temp = listNew.get(0);
                isFirst = true;
                listNew.remove(0);
                listNew.add(temp);
            }
            ArrayList<String> list = new ArrayList<>(listNew);

            if (counter == listNew.size()) break;
            if (!escaped) break;
            resultList.clear();
            while (resultList.size() <= length) {
                escaped = false;
                boolean found = false;
                int i = 0;
                if (isFirst) {
                    isFirst = false;
                    resultList.add(list.get(0));
                    letter = list.get(0).substring(list.get(0).length() - 1);
                    list.remove(0);
                    found = true;

                } else {

                    for (; i < list.size(); i++) {
                        if (list.get(i).startsWith(letter.toUpperCase())) {
                            resultList.add(list.get(i));
                            letter = list.get(i).substring(list.get(i).length() - 1);
                            list.remove(i);
                            found = true;
                            break;
                        }
                    }

                }
                if (!found) {
                    escaped = true;
                    break;
                }

            }
            counter++;

        }
        isFirst = true;
        for (String entry: resultList) {
            if (!isFirst) {
                result.append(" ");
            }else isFirst = false;
            result.append(entry);

        }
        return result;
    }
}
