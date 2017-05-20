package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();

        /*URL className = Solution.class.getProtectionDomain().getCodeSource().getLocation();
        String classPath = className.getPath();
        classPath = classPath.replace("/out/production", "");
        String absoluteStringPath = classPath.substring(1) + fileName;*/
        String absoluteStringPath = fileName;
        try {
            if ("xml".equals(absoluteStringPath.substring(absoluteStringPath.lastIndexOf(".") + 1))) {
                properties.loadFromXML(new FileInputStream(new File(absoluteStringPath)));
            } else {
                properties.load(new FileReader(absoluteStringPath));
            }

        }catch (IOException e) {
        }

        return properties;
    }
}
