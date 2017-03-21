package com.javarush.task.task26.task2609;

/* 
Распределение элементов по корзинам с собственным локом
*/
public class Solution {
    private static final int NUMBER_LOCKS = 12;
    private final Node[] buckets;
    private final Object[] locks;

    static class Node {
        public Node next;
        public Object key;
        public Object value;
    }

    public Solution(int numberBuckets) {
        buckets = new Node[numberBuckets];
        locks = new Object[NUMBER_LOCKS];
        for (int i = 0; i < NUMBER_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % NUMBER_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key)) return m.value;
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % NUMBER_LOCKS]) {
                buckets[i] = null;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution(3);
        Node node1 = new Node();
        node1.key = 1;
        Node node2 = new Node();
        node1.key = 2;
        Node node3 = new Node();
        node1.key = 3;
        sol.buckets[0] = node1;
        sol.buckets[1] = node2;
        sol.buckets[2] = node3;
        sol.get(1);
        sol.clear();

    }
}
