package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by Archmage on 19.03.2017.
 */
public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map){
        this.map = map;
    }

    @Override
    public void run() {
        int i = 0;
        try{
            while(true) {
                i++;
                map.put(String.valueOf(i), String.format("Some text for %s", i));
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            System.out.println(String.format("%s thread was terminated", Thread.currentThread().getName()));
        }
    }
}
