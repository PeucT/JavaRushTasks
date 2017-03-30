package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ArchMage on 30.03.17.
 */
public class MyThread extends Thread{
    private static AtomicInteger count = new AtomicInteger(0);

    private int increment(){
        if (count.get() == Thread.MAX_PRIORITY) {
            count.set(Thread.MIN_PRIORITY);
            return 1;
        } else {
            count.set(count.incrementAndGet());
            int tempCount = count.get();
            if (tempCount > Thread.currentThread().getThreadGroup().getMaxPriority()) {
                return Thread.currentThread().getPriority();
            } else {
                return tempCount;
            }
        }
    }

    public MyThread() {
        this.setPriority(increment());
    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(increment());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.setPriority(increment());
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(increment());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.setPriority(increment());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(increment());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.setPriority(increment());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        this.setPriority(increment());
    }
}
