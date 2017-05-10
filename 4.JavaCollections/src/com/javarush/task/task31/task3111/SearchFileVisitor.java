package com.javarush.task.task31.task3111;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private int maxSize;
    private String partOfName;
    private String partOfContent;
    private int minSize;

    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean found = true;
        if (maxSize != 0) {
            if (Files.size(file) > maxSize) { found = false; }
        }

        if (minSize != 0) {
            if (Files.size(file) < minSize) { found = false; }
        }

        if (partOfName != null) {
            if (!(file.getName(file.getNameCount() - 1).toString()).contains(partOfName)) { found = false; }
        }

        if (partOfContent != null) {
            String content = new String(Files.readAllBytes(file));
            if (!content.contains(partOfContent)) { found = false; }


        }

        if (found) {
            foundFiles.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public void setPartOfName(String partOfName){
        this.partOfName = partOfName;
    }
    public void setPartOfContent(String partOfContent){
        this.partOfContent = partOfContent;
    }
    public void setMinSize(int minSize){
        this.minSize = minSize;

    }
    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles(){
        return foundFiles;
    }
}
