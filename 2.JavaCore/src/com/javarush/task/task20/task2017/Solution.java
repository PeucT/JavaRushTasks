package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;


/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {
        try
        {
            Object o = objectStream.readObject();
            if (o instanceof B) { return (B) o ;}
            else { return (A) o ;}

        } catch (ClassNotFoundException e) {
            System.out.println("Error happens");
            return null;
        } catch (IOException e1) {
            System.out.println("Error happens");
            return null;
        } catch (Exception e2) {
            System.out.println("Error happens");
            return null;
        }

    }

    public class A implements Serializable{
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
