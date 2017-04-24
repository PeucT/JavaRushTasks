package com.javarush.task.task27.task2712.ad;

/**
 * Created by ArchMage on 18.04.17.
 */
public class Advertisement {
    public Object content;
    public String name;
    private long initialAmount;
    private int hits;
    public int duration;

    // Часть 8
    //1. В классе Advertisement создай поле long amountPerOneDisplaying.
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits;
    }

    // 2. В классе Advertisement создай геттеры для полей name, duration и amountPerOneDisplaying.


    public String getName() {
        return name;
    }

    public int getHits() {
        return hits;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void revalidate(){
        if (hits <= 0) { throw new UnsupportedOperationException(); }
        hits--;
    }
}
