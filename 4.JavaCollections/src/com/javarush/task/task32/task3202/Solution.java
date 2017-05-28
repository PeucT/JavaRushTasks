package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //StringWriter writer = getAllDataFromInputStream(new FileInputStream("F:\\JavaProjects\\InputSource\\raf.txt"));
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        StringBuilder strB = new StringBuilder();
        if (is != null) {
            while (is.available() > 0) {
                strB.append((char) is.read());
            }
            writer.write(strB.toString());
        }
        return writer;
    }
}