package com.javarush.task.task26.task2610;

import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

/**
 * Created by Archmage on 19.03.2017.
 */
public class Consumer implements Runnable {
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            while (true){
                String input;
                if ((input = (String) queue.take()) != null) {
                    System.out.println(input);
                }
                Thread.sleep(300);
            }
        } catch (InterruptedException e){
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
