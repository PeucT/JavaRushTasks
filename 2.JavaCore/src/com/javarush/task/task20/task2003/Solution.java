package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        load(new FileInputStream(reader.readLine()));
        reader.close();

    }


    public void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties();

        for (Map.Entry<String, String> entry: properties.entrySet()) {
            prop.put(entry.getKey(), entry.getValue());
        }
        prop.save(outputStream, "saved");

    }

    public void load(InputStream inputStream) throws Exception {
        Properties prop = new Properties();
        prop.load(inputStream);

        properties.clear();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            properties.put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public static void main(String[] args) throws Exception {

    }
}
