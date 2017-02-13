package com.javarush.task.task20.task2015;

import java.io.*;

/*
Переопределение сериализации
*/

public class Solution implements Runnable, Serializable {
    private transient Thread runner;
    private int speed;


    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, does not matter
    }



    /*
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(speed);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        speed = in.readInt();
        runner = new Thread(this);
        runner.start();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

    }
}

