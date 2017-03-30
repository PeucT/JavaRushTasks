package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    public static class AmigoThreadFactory implements ThreadFactory{
        private static AtomicInteger fabricsCount = new AtomicInteger(0);
        private AtomicInteger threadsCount = new AtomicInteger(0);

        public AmigoThreadFactory(){
            fabricsCount.set(fabricsCount.incrementAndGet());
        }

        @Override
        public Thread newThread(Runnable r) {
            String groupName = Thread.currentThread().getThreadGroup().getName();

            threadsCount.set(threadsCount.incrementAndGet());
            Thread thread = new Thread(String.format("%s-pool-%d-thread-%d", groupName, fabricsCount.get(), threadsCount.get() ));
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            System.out.println(thread.getName());
            return thread;
        }
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
