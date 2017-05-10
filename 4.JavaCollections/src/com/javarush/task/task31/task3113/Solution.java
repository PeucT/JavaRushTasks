package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {
    private static int dirCount;
    private static int filesCount;
    private static long volume;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathToDirectory = reader.readLine();
        reader.close();
        Path path = Paths.get(pathToDirectory);
        if (path == null || !Files.isDirectory(path)) {
            System.out.println(path.toString() + " - не папка");
            return;
        }
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                filesCount++;
                volume += Files.size(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException{
                dirCount++;
                return super.preVisitDirectory(dir, attrs);
            }
        });

        System.out.println("Всего папок - " + (dirCount - 1));
        System.out.println("Всего файлов - " + filesCount);
        System.out.println("Общий размер - " + volume);

    }
}
