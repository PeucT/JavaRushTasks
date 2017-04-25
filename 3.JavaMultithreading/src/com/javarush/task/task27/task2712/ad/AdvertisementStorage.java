package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArchMage on 18.04.17.
 */
public class AdvertisementStorage {
    private static AdvertisementStorage instance = new AdvertisementStorage();

    // Часть 7
    // 1. Видео должно где-то храниться, пусть это будет список.
    private final List<Advertisement> videos = new ArrayList<>();


    // 3. В конструкторе класса добавим в список videos какие-то данные.
    // ЕСЛИ НЕ ВЕРНУТЬ СПИСОК ВИДЕО В ИСХОДНОЕ СОСТОЯНИЕ, ТО 14 ПУНКТ ЗАДАЧИ НЕ ПРОХОДИТ!!!
    // ОШИБКА: "Перед отображением видео должно быть зарегистрировано событие "видео выбрано".

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
        /*videos.add(new Advertisement(someContent, "First Video", 100, 1, 100));
        videos.add(new Advertisement(someContent, "Second Video", 120, 1, 120));
        videos.add(new Advertisement(someContent, "Third Video", 400, 1, 400));
        videos.add(new Advertisement(someContent, "Fourth Video", 360, 1, 359));
        videos.add(new Advertisement(someContent, "FourthMoreMoney Video", 360, 1, 360));
        videos.add(new Advertisement(someContent, "Fifth Video", 30, 1, 30));
        videos.add(new Advertisement(someContent, "Sixth Video", 45, 1, 45));*//*
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 15 * 60)); // 15 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 3 * 60)); //3 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
        //videos.add(new Advertisement(someContent, "Forth Video", 12, 3, 1 * 60)); //1 min
        //videos.add(new Advertisement(someContent, "Fifth Video", 8, 2, 1 * 60)); //1 min
        //videos.add(new Advertisement(someContent, "Sixth Video", 16, 8, 1 * 60)); //1 min*/
    }
    // 2. Чтобы как-то работать с видео, создай публичные методы:
    // 2.1. list() — который вернет список всех существующих доступных видео.
    public List<Advertisement> list(){
        return videos;
    }
    // 2.2. add(Advertisement advertisement) — который добавит новое видео в список videos.
    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }



    public static AdvertisementStorage getInstance(){
        return instance;

        // Ленивую инициализацию делать нельзя. Валидатор ругается.
        /*if (instance == null) {
            return new AdvertisementStorage();
        } else {
            return instance;
        }*/
    }
}
