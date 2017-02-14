package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        //out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName, true);
        //in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution sol = new Solution("D:\\JavaProjects\\IO\\input.txt");
        sol.writeObject("Some data");

        OutputStream out = new FileOutputStream(new File("D:\\JavaProjects\\IO\\output.txt"));
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(sol);
        objOut.close();
        ObjectInputStream inpObj = new ObjectInputStream(new FileInputStream(new File("D:\\JavaProjects\\IO\\output.txt")));
        Solution loadedSol = new Solution("D:\\JavaProjects\\IO\\ttinput.txt");
        loadedSol = (Solution) inpObj.readObject();


    }
}
