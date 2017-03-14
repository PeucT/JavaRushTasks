package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (Thread entry: threads){
            switch (entry.getState()){
                case NEW:
                    entry.start();
                    break;
                case WAITING:
                    entry.interrupt();
                    break;
                case BLOCKED:
                    entry.interrupt();
                    break;
                case TIMED_WAITING:
                    entry.interrupt();
                    break;
                case RUNNABLE:
                    entry.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(entry.getPriority());
                    break;
            }
        }
    }

    public static void main(String[] args) {

    }
}
