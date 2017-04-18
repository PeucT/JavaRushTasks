package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

/**
 * Created by ArchMage on 18.04.17.
 */
public class AdvertisementManager {
    // Часть 7
    // 4. В AdvertisementManager создадим final поле-ссылку на экземпляр AdvertisementStorage и назовем ее storage.
    public final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    // В классе AdvertisementManager создай конструктор, который принимает один параметр — int timeSeconds.
    // Создай соответствующее поле и сохраните это значение в него.
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    // 4. AdvertisementManager выполняет только одно единственное действие — обрабатывает рекламное видео.
    // Поэтому создайте единственный публичный метод void processVideos(), его функционал опишем в следующем задании.
    // А пока выведем в консоль «calling processVideos method»
    public void processVideos(){
        ConsoleHelper.writeMessage("calling processVideos method");
    }
}
