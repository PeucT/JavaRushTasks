package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<K, V>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V object = cache.get(key);
        if (object == null) {
            Constructor<V> constr = clazz.getConstructor(key.getClass());
            object = constr.newInstance(key);
            cache.put(key, object);
        }
        //TODO add your code here
        return object;
    }

    public boolean put(V obj) {
        try {
            Method meth = obj.getClass().getDeclaredMethod("getKey", null);
            meth.setAccessible(true);
            K key = (K) meth.invoke(obj,null);
            cache.put(key, obj);
            return true ;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        }
        //TODO add your code here

    }

    public int size() {
        return cache.size();
    }
}
