package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Запрет сериализации
*/
public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        public void readObject(InputStream inp) throws NotSerializableException {
            throw new NotSerializableException();
        }

        public void writeObject(OutputStream out) throws NotSerializableException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) {

    }
}
