package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

/**
 * Created by Archmage on 31.05.2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private final static Object PRESENT = new Object();
    private transient HashMap<E,Object> map;


    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }


    @Override
    public Object clone(){
        try {
            HashMap<E, Object> newMap = (HashMap<E, Object>) map.clone();
            AmigoSet<E> newAmigoSet = new AmigoSet<E>();
            newAmigoSet.map = newMap;
            return newAmigoSet;
        }catch (Exception ex){
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeInt(map.size());
        for (E entry: map.keySet()){
            out.writeObject(entry);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Float loadFactor = in.readFloat();
        int capacity = in.readInt();
        int size = in.readInt();
        map = new HashMap<E, Object>(capacity, loadFactor);
        for (int i = 0; i < size; i++){
            map.put((E) in.readObject(), PRESENT);
        }


    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contains(Object o) {
        return null != map.get((E) o);
    }

    public void clear(){
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return null != map.remove(o);
    }

    @Override
    public int size() {
        return map.size();
    }

    public AmigoSet(){
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection){
        map = new HashMap<E, Object>((16 > collection.size()/.75f ? 16 : (int)(collection.size()/.75f + 1)));
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e, PRESENT);
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AmigoSet<Integer> amigoSet = new AmigoSet<>();
        amigoSet.add(4);
        amigoSet.add(5);

        /*ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("F:\\JavaProjects\\InputSource\\out.txt")));
        out.writeObject(amigoSet);
        out.close();*/
        //System.out.println(amigoSet.remove(3));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("F:\\JavaProjects\\InputSource\\out.txt")));
        amigoSet = (AmigoSet<Integer>) in.readObject();
        System.out.println(amigoSet);
    }
}
