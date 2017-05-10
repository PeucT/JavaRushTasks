package com.javarush.task.task31.task3112;

import com.sun.webkit.network.URLs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        Path tempFile = Files.createTempFile("temp", "temp");

        InputStream input = url.openStream();
        Files.copy(input, tempFile);
        String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);
        Path outFile = Paths.get(downloadDirectory + File.separator + fileName);
        Files.move(tempFile, outFile);

        return outFile;
        // implement this method
    }
}
