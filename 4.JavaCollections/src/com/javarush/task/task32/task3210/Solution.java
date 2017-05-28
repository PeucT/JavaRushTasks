package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        if (args[0] == null || args[1] == null || args[2] == null) { return; }
        try ( RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        ) {
            byte[] buffer = new byte[args[2].length()];
            raf.seek(Integer.parseInt(args[1]));
            long length = 0;
            if ( (length = (raf.length() - Integer.parseInt(args[1]))) > buffer.length ) {
                raf.read(buffer, 0, buffer.length);
            } else {
                raf.read(buffer, 0, (int) length);
            }
            raf.seek(raf.length());
            if (args[2].equals(convertByteToString(buffer))){
                raf.write("true".getBytes());
            }else {
                raf.write("false".getBytes());
            }
        } catch (IOException e) {
        }
    }

    public static String convertByteToString(byte[] readBytes){
        return new String(readBytes);
    }
}
