package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    private List<Advertisement> videosList;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    // 4. AdvertisementManager выполняет только одно единственное действие — обрабатывает рекламное видео.
    // Поэтому создайте единственный публичный метод void processVideos(), его функционал опишем в следующем задании.
    // А пока выведем в консоль «calling processVideos method»

    //Часть 9
    //2.2. Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду.
    //2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости показа
    // одного рекламного ролика в копейках. Вторичная сортировка — по увеличению стоимости показа одной секунды
    // рекламного ролика в тысячных частях копейки.

    public void processVideos() throws NoVideoAvailableException{
        List<Advertisement> availableVideos = new ArrayList<Advertisement>();
        for (Advertisement entry: storage.list()){
            if (entry.getHits() > 0) { availableVideos.add(entry); }
        }
        //Collections.copy(storage.list(), availableVideos);
        if (availableVideos == null || availableVideos.size() == 0) {
            throw new NoVideoAvailableException();
        } else videoFitting(availableVideos, new ArrayList<Advertisement>());

        if (videosList == null || videosList.size() == 0) {
            throw new NoVideoAvailableException();
        }

        Collections.sort(videosList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if (Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying()) == 0){
                    return Long.compare(o1.getAmountPerOneDisplaying() * 1000 / o1.duration, o2.getAmountPerOneDisplaying() * 1000 / o2.duration);
                }else return Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
            }
        });

        StatisticManager.getInstance().register(
                new VideoSelectedEventDataRow(
                        videosList,
                        totalAmount(videosList),
                        totalDuration(videosList)));

        for (Advertisement entry: videosList) {
            ConsoleHelper.writeMessage(String.format("%s  is displaying... %d, %d",
                    entry.getName(),
                    entry.getAmountPerOneDisplaying(),
                    entry.getAmountPerOneDisplaying() * 1000 / entry.getDuration()));
            entry.revalidate();
        }
    }

    private void videoFitting(List<Advertisement> availableVideos, List<Advertisement> fittingList){
        //List<Advertisement> newAvailableList = new ArrayList<Advertisement>();
        boolean isAlreadyMadeAdd = false;
        for (int i = 0; i < availableVideos.size(); i++){
            if (isAlreadyMadeAdd) {
                fittingList.remove(fittingList.size() - 1);
                isAlreadyMadeAdd = false;
            }
            if (totalDuration(fittingList) + availableVideos.get(i).duration <= timeSeconds) {
                fittingList.add(availableVideos.get(i));
                isAlreadyMadeAdd = true;
                ArrayList<Advertisement> newAvailableList = new ArrayList<Advertisement>(availableVideos);
                newAvailableList.remove(i);
                videoFitting(newAvailableList, fittingList);
            }
        }
        if (isAlreadyMadeAdd) {
            fittingList.remove(fittingList.size() - 1);
            isAlreadyMadeAdd = false;
        }
        if (totalAmount(videosList) < totalAmount(fittingList)) {
            videosList = new ArrayList<Advertisement>(fittingList);
        } else if (totalAmount(videosList) == totalAmount(fittingList)) {
            if (totalDuration(videosList) < totalDuration(fittingList)) {
                videosList = new ArrayList<Advertisement>(fittingList);
            } else if (totalDuration(videosList) == totalDuration(fittingList)) {
                if (videosList.size() > fittingList.size()) {
                    videosList = new ArrayList<Advertisement>(fittingList);
                }
            }
        }
    }

    private static int totalDuration(List<Advertisement> list){
        if (list == null || list.size() == 0) { return 0; }
        int duration = 0;
        for (Advertisement entry: list){
            duration += entry.duration;
        }
        return duration;
    }

    private static long totalAmount(List<Advertisement> list){
        if (list == null || list.size() == 0) { return 0; }
        long amount = 0;
        for (Advertisement entry: list){
            amount += entry.getAmountPerOneDisplaying();
        }
        return amount;
    }
}
