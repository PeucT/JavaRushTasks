package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //System.out.println(new Solution(4));
        FileOutputStream outStream = new FileOutputStream(new File("D:\\JavaProjects\\IO\\input.txt"));
        ObjectOutputStream out = new ObjectOutputStream(outStream);
        Solution savedObject = new Solution(6);
        out.writeObject(savedObject);

        out.close();
        FileInputStream inputSTream = new FileInputStream(new File("D:\\JavaProjects\\IO\\input.txt"));
        ObjectInputStream input = new ObjectInputStream(inputSTream);
        Solution loadedObject = new Solution(10);
        loadedObject = (Solution) input.readObject();

        if ((loadedObject.toString()).equals(savedObject.toString())) {
            System.out.println("Equals");
            System.out.println(savedObject.toString());
            System.out.println(loadedObject.toString());
        } else {
            System.out.println("Not");
            System.out.println(savedObject.toString());
            System.out.println(loadedObject.toString());
        }


    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    transient private int temperature;
    String string;


    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }

}
