package com.javarush.task.task25.task2511;

import java.util.TimerTask;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;

        class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                String message = e.getMessage();
                String name = Thread.currentThread().getName();
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < name.length(); i++){
                    str.append("*");
                }
                message = message.replaceAll(name, str.toString());
                System.out.println(message);
            }
        }

        this.handler = new MyUncaughtExceptionHandler();    //init handler here


    }

    public void run() {
        try {
            original.run();
            throw new Throwable();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        Solution sol = new Solution(new TimerTask() {
            @Override
            public void run() {

            }
        });
        Thread thread = new Thread(sol);
        thread.start();
    }


}