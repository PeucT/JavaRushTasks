package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;


/**
 * Created by ArchMage on 10.04.17.
 */
public class HTMLFileFilter extends FileFilter {


    @Override
    public boolean accept(File f) {
        if (!f.isDirectory()) {
            String fileName = f.getName();
            fileName = fileName.toLowerCase();
            if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
                return true;
            } else {
                return false;
            }
        }
        else { return true; }
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
