package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

/**
 * Created by ArchMage on 24.04.17.
 */
public class StatisticManager {
    private StatisticManager instance = new StatisticManager();

    private StatisticManager(){

    }

    public StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data){

    }
}
