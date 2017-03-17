package com.javarush.task.task25.task2506;

/**
 * Created by PAKOT on 14.03.2017.
 */
public class LoggingStateThread extends Thread {
    private Thread target;
    private State prevState = null;
    public LoggingStateThread (Thread target){
        this.target = target;
        this.setDaemon(true);
    }

    public void run(){

        while (true){
            State state = target.getState();
            if (prevState == null) {
                prevState = state;
                System.out.println(state.toString());
            } else if (!state.equals(prevState)) {
                System.out.println(state.toString());
                prevState = state;
            }
            if (state.equals(State.TERMINATED)) { break; }

        }
    }
}
