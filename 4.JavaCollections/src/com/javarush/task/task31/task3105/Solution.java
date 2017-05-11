package com.javarush.task.task31.task3105;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/* 
Добавление файла в архив
*/
public class Solution {
    private static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        if (args[0] == null || args[1] == null) { return; }
        if (!args[1].toString().endsWith(".zip")) { return; }
        Path pathToFile = Paths.get(args[0]);

        FileInputStream zipInputAsFile = new FileInputStream(args[1]);
        ZipInputStream zipInput = new ZipInputStream(zipInputAsFile);


        ZipEntry zipEntry;
        while ((zipEntry = zipInput.getNextEntry()) != null) {
            if (!zipEntry.isDirectory()) {
                StringBuilder buffer = new StringBuilder();

                while (zipInput.available() > 0) {
                    int symbol = zipInput.read();
                    if (symbol != -1) {
                        buffer.append((char) symbol);
                    }
                }

                map.put(zipEntry.getName(), buffer.toString());
            }
        }
        zipInput.close();
        zipInputAsFile.close();



        FileOutputStream zipOutAsFile = new FileOutputStream(args[1]);
        ZipOutputStream zipOut = new ZipOutputStream(zipOutAsFile);

        boolean isPresent = false;

        for (Map.Entry<String, String> mapEntry : map.entrySet()) {
            zipOut.putNextEntry(new ZipEntry(mapEntry.getKey()));

            if (mapEntry.getKey().contains("/")) {
                if (mapEntry.getKey().substring(mapEntry.getKey().lastIndexOf("/") + 1).equals(Paths.get(args[0]).getFileName().toString())){
                    Files.copy(Paths.get(args[0]), zipOut);
                    isPresent = true;
                } else {
                    zipOut.write((mapEntry.getValue().getBytes()));

                }
            }else {
                if (mapEntry.getKey().equals(Paths.get(args[0]).getFileName().toString())) {
                    Files.copy(Paths.get(args[0]), zipOut);
                    isPresent = true;
                } else {
                    zipOut.write((mapEntry.getValue().getBytes()));
                }
            }
            //zipOut.write((mapEntry.getValue().getBytes()));
        }
        if (!isPresent) {
            zipOut.putNextEntry(new ZipEntry("new/" + Paths.get(args[0]).getFileName().toString()));
            Files.copy(Paths.get(args[0]), zipOut);
        }

        zipOut.close();
        zipOutAsFile.close();

    }
}
