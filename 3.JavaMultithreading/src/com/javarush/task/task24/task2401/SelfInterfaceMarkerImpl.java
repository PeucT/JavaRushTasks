package com.javarush.task.task24.task2401;

/**
 * Created by PAKOT on 08.03.2017.
 */
public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker {
    public void sowSomething(){
        System.out.println("Something");
    }

    public void calc(int i, int b){
        System.out.println(i + b);
    }
}
