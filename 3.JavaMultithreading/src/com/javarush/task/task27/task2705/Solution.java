package com.javarush.task.task27.task2705;

/* 
Второй вариант deadlock
*/
public class Solution {
    private final Object lock = new Object();

    public synchronized void firstMethod() {
        synchronized (lock) {
            doSomething();
        }
    }

    public void secondMethod() {
        synchronized (lock) {
            synchronized (this) {
                doSomething();
            }
        }
    }

    private void doSomething() {
    }

    public static void main(String[] args) throws InterruptedException {
        final Solution sol = new Solution();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sol.firstMethod();
            }
        });
        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sol.secondMethod();
            }
        }).start();

        Thread.sleep(1000);
        System.out.println(thread.getState());
    }
}