package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread thread;

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.currentThread().sleep(75);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void start(String threadName) {
        TaskManipulator manipulator = new TaskManipulator();
        thread = new Thread(manipulator);
        thread.setName(threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
