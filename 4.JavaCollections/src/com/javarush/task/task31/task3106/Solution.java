package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args == null) { return; }
        List<String> pathsList = new ArrayList<String>();

        for (int i = 1; i < args.length; i++) {
            pathsList.add(args[i]);
        }

        Collections.sort(pathsList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.substring(o1.length() - 3).compareTo(o2.substring(o2.length() - 3));
            }
        });

        List<FileInputStream> streamsLists = new ArrayList<>();
        for (int i = 0; i < pathsList.size(); i++) {
            streamsLists.add(new FileInputStream(pathsList.get(i)));
        }

        ZipInputStream fullZipInput = new ZipInputStream(new SequenceInputStream(Collections.enumeration(streamsLists)));
        ZipEntry zipEntry = null;
        FileOutputStream out = new FileOutputStream(new File(args[0]));
        while ((zipEntry = fullZipInput.getNextEntry()) != null) {
            int symbol = 0;
            int count;
            byte[] buff = new byte[1024 * 1024];
            while ((count = fullZipInput.read(buff)) != -1) {
                out.write(buff, 0, count);
            }
            out.flush();
        }
        fullZipInput.close();
        out.close();

    }
}
