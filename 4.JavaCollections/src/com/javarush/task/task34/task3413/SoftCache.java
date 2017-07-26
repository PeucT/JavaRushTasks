package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        AnyObject obj = null;
        if (softReference == null) {
            return null;
        }else {
            obj = softReference.get();
            softReference.clear();
            return obj;
        }

        //напишите тут ваш код
    }

    public AnyObject put(Long key, AnyObject value) {

        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        value = null;
        AnyObject obj = null;
        if (softReference == null) {
            return null;
        }else {
            obj = softReference.get();
            softReference.clear();
            return obj;
        }
        //напишите тут ваш код
    }

    public AnyObject remove(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.remove(key);
        AnyObject obj = null;
        if (softReference == null) {
            return null;
        }else {
            obj = softReference.get();
            softReference.clear();
            return obj;
        }
        //напишите тут ваш код
    }
}