package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by ArchMage on 24.04.17.
 */
public interface EventDataRow {
    EventType getType();
    Date getDate();
    int getTime();
}
