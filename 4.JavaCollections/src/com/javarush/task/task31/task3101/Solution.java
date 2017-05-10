package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        if (args[0] == null || args[1] == null){ return; }
        File baseOutFile = new File(args[1]);
        Path dirPath = Paths.get(args[0]);
        File outFile = new File(baseOutFile.getParent() + File.separator + "allFilesContent.txt");
        FileUtils.renameFile(baseOutFile, outFile);
        ArrayList<File> fileList = new ArrayList<>();

        try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
            Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
                    File currentFile = new File(file.toString());
                    if (currentFile.isFile()) {
                        if (currentFile.length() > 50) {
                            FileUtils.deleteFile(currentFile);
                        } else {
                            fileList.add(currentFile);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

            });

            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File entry: fileList) {
                byte[] content = Files.readAllBytes(entry.toPath());
                outputStream.write(content);
                outputStream.write('\n');
            }



        } catch (IOException e) {

        }
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
