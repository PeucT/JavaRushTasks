package com.javarush.task.task27.task2707;

import java.util.Date;

/*
Определяем порядок захвата монитора
*/
public class Solution {

    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2){
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    private void referenceSyncMethod1(Object obj1) {
        synchronized (obj1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private void referenceSyncMethod2(Object obj1) {
        synchronized (obj1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                solution.referenceSyncMethod1(o1);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                solution.referenceSyncMethod2(o2);
            }
        });

        thread2.start();
        Thread.sleep(100);
        thread.start();
        thread3.start();
        Thread.sleep(100);
        if (thread3.getState().equals(Thread.State.TERMINATED)) { return true;}
        else { return false; }

    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
