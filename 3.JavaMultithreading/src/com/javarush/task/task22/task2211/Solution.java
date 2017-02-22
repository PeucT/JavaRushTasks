package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        try {
            if (args[0] != null && args[1] != null) {
                byte[] arr;
                BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
                boolean isFirst = true;
                File file = new File(args[1]);
                FileWriter writer = new FileWriter(file);
                while (true) {
                    String line = reader.readLine();
                    if (line == null) break;
                    arr = line.getBytes(Charset.forName("Windows-1251"));
                    if (isFirst) { isFirst = false; }
                    else { writer.write(System.lineSeparator()); }
                    //else { writer.write(new String(System.lineSeparator().getBytes(), Charset.forName("UTF-8"))); }
                    writer.write(new String(arr, Charset.forName("UTF-8")));
                }

            }
        } catch (Exception e) {}
    }
}
