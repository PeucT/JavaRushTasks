package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            return;
        }
        List<String> list = new ArrayList<>();
        List list1 = new ArrayList<>();
        for (int i = 1; i< args.length; i++) {
            list.add(args[i]);
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            list1.add(new FileInputStream(list.get(i)));
        }
        byte[] bytes = new byte[1024];
        String resultFileName = args[0];
        int readByteCnt;
        try (ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(list1)));
             ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(resultFileName))) {
            while (zipInputStream.available() >0) {
                zipInputStream.getNextEntry();
                while ((readByteCnt = zipInputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, readByteCnt);
                    outputStream.flush();
                }
            }
        }
    }
}
