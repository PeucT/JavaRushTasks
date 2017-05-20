package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    static Entry<String> root = new Entry<String>("0");
    private int size = 0;
    private Queue<Entry<String>> queue = new LinkedList<>();
    {
        queue.add(root);
    }

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        //CustomTree list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        //System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("3");
        list.add("16");
        //System.out.println(list.size());
        //System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));


    }



    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index){
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        //return queue.size() - 1;
        //return recurrSize(root) - 1;
        return size;
    }

    private int recurrSize(Entry<String> entry){
        if (entry.availableToAddLeftChildren) {
            if (entry.availableToAddRightChildren) { return 1; }
            else { return 1 + recurrSize(entry.rightChild); }
        } else if (entry.availableToAddRightChildren) {
            return  1 + recurrSize(entry.leftChild);
        } else {
            return 1 + recurrSize(entry.leftChild) + recurrSize(entry.rightChild);
        }
    }

    public boolean add(String s){

        for (Entry<String> entry: queue) {
            if (entry.isAvailableToAddChildren()) {
                if (entry.availableToAddLeftChildren) {
                    entry.leftChild = new Entry<String>(s);
                    if (!"0".equals(entry.elementName)) {
                        entry.leftChild.parent = entry;
                    }
                    size++;
                    queue.add(entry.leftChild);
                    entry.checkChildren();

                    return true;
                }else {
                    entry.rightChild = new Entry<String>(s);
                    if (!"0".equals(entry.elementName)) {
                        entry.rightChild.parent = entry;
                    }
                    entry.checkChildren();
                    size++;
                    queue.add(entry.rightChild);
                    return true;
                }
            }
        }
        return false;
    }

    public String getParent(String s){
        for (Entry<String> entry : queue){
            if (s.equals(entry.elementName)) {
                return entry.parent.elementName;
            }
        }
        return null;
    }

    private int getDepth(int index){
        int power = 0;
        int value = 0;
        while (value + (int) Math.round(Math.pow(2, power)) < index + 1){
            value = value + (int) Math.round(Math.pow(2, power));
            power += 1;

        }
        return power;
    }

    @Override
    public boolean remove(Object o) {
        return remove((String) o);
    }

    public boolean remove(String s){

        Entry<String> forRemove = null;
        for (Entry<String> entry: queue){
            if (s.equals(entry.elementName)) {
                forRemove = entry;
                break;
            }
        }
        if (forRemove != null){
            recurrRemove(forRemove);
            if (forRemove.parent.leftChild.elementName.equals(forRemove.elementName)){
                forRemove.parent.leftChild = null;
                return true;
            } else {
                forRemove.parent.rightChild = null;
                return true;
            }
            //queue.remove(forRemove);
        } else { return false; }

    }

    private void recurrRemove(Entry<String> entry){
        if (!entry.isAvailableToAddChildren()) {
            recurrRemove(entry.rightChild);
            recurrRemove(entry.leftChild);
            queue.remove(entry);
            size--;
        }else if (!entry.availableToAddLeftChildren){
            recurrRemove(entry.leftChild);
            queue.remove(entry);
            size--;
        }else {
            queue.remove(entry);
            size--;
        }
    }

    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren(){
            if (leftChild != null) { availableToAddLeftChildren = false; }
            if (rightChild != null) { availableToAddRightChildren = false; }
        }
        boolean isAvailableToAddChildren() {
            if (availableToAddLeftChildren || availableToAddRightChildren) { return true; }
            else { return false; }
        }
    }

}
