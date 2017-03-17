package com.javarush.task.task25.task2512;

import java.util.ArrayList;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        ArrayList<String> trace = new ArrayList<String>();
        //trace.add(e.toString());
        while (true){
            if ( e.getCause() == null ) {
                trace.add(e.toString());
                break;
            }
            trace.add(e.toString());
            e = e.getCause();

        }
        for (int i = trace.size() - 1; i >= 0; i--) {
            System.out.println(trace.get(i));
        }
    }

    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();
        sol.uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
