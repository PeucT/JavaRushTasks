package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        for (int k = 0; k < words.length; k++){
            Word resultString = new Word(words[k]);
            for (int i = 0; i < crossword.length; i ++){
                for (int j = 0; j < crossword[0].length; j++){
                    if (crossword[i][j] == words[k].charAt(0) ) {

                        // Чтобы не заморачиваться проверкой выхода за границы массива, оборачиваем все обращения
                        // к ячейкам соседним с рассматриваемой ячейкой (в.т.ч. внутри метода сравнения)

                        // Исходя из координат внутри массива 2ого символа слова, определяем вектор направления
                        // проверки на полное нахождение слова в массиве
                        try {
                            if (crossword[i][j + 1] == words[k].charAt(1)) {
                                checkMatch(list, crossword, words[k], resultString, 0, 1, i, j);
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (crossword[i - 1][j + 1] == words[k].charAt(1)) {
                                checkMatch(list, crossword, words[k], resultString, -1, 1, i, j);
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (crossword[i - 1][j] == words[k].charAt(1)) {
                                checkMatch(list, crossword, words[k], resultString, -1, 0, i, j);
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (crossword[i - 1][j - 1] == words[k].charAt(1)) {
                                checkMatch(list, crossword, words[k], resultString, -1, -1, i, j);
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (crossword[i][j - 1] == words[k].charAt(1)) {
                                checkMatch(list, crossword, words[k], resultString, 0, -1, i, j);
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (crossword[i + 1][j - 1] == words[k].charAt(1)) {
                                checkMatch(list, crossword, words[k], resultString, 1, -1, i, j);
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (crossword[i + 1][j] == words[k].charAt(1)) {
                                checkMatch(list, crossword, words[k], resultString, 1, 0, i, j);
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if (crossword[i + 1][j + 1] == words[k].charAt(1)) {
                                checkMatch(list, crossword, words[k], resultString, 1, 1, i, j);
                            }
                        } catch (Exception e) {
                        }
                    }

                }
            }
        }
        for (Word entry: list){
            System.out.println(entry.toString());
        }
        return list;
    }

    // Направление проверки получено, выполняем проверку для вхождения всего слова
    private static void checkMatch(List<Word> list, int[][] crossword, String wordText,Word word, int deltaY, int deltaX, int startY, int startX) throws Exception{
        boolean isNotMatch = false;
        int step = 1;
        for (int i = 2; i < wordText.length(); i++){
            step++;
            if (crossword[startY + step*deltaY][startX + step*deltaX] != wordText.charAt(i)) {
                isNotMatch = true;
            }

        }
        if (!isNotMatch){
            word.setStartPoint(startX, startY);
            word.setEndPoint(startX + deltaX * step, startY + deltaY * step);
            list.add(word);
            word = new Word(wordText);
        }
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
