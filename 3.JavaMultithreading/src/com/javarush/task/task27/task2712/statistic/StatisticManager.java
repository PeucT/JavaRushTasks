package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by ArchMage on 24.04.17.
 */
public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<Cook>();

    private StatisticManager(){

    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    public void register(Cook cook){
        cooks.add(cook);
    }

    public Map<Date, Long> getAmountPerDay(){
        return statisticStorage.amountPerDay();
    }

    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage = new HashMap<EventType, List<EventDataRow>>();;

        public StatisticStorage(){

            for (EventType entry: EventType.values()){
                storage.put(entry, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            EventType eventType = data.getType();
            List<EventDataRow> list = storage.get(eventType);
            list.add(data);
        }

        private Map<Date, Long> amountPerDay(){
            //generateStatistic();

            Map<Date, Long> amountPerDay = new TreeMap<Date, Long>(Collections.reverseOrder());
            List<EventDataRow> advertismentList = storage.get(EventType.SELECTED_VIDEOS);
            for (EventDataRow entry: advertismentList){
                VideoSelectedEventDataRow row = (VideoSelectedEventDataRow) entry;
                Date advDate = row.getDate();

                Date referenceDate = null;
                try {
                    referenceDate = new SimpleDateFormat( "dd.MM.yyyy" ).parse("" + advDate.getDate() + "."
                            + (advDate.getMonth() + 1) + "."
                            + (advDate.getYear() + 1900) );

                } catch (ParseException e) {
                }

                Long amountEntry = amountPerDay.get(referenceDate);
                if (amountEntry == null) {
                    amountPerDay.put(referenceDate, row.getAmount());
                } else {
                    amountPerDay.put(referenceDate, row.getAmount() + amountEntry);
                }
            }

            return amountPerDay;
        }

        private void generateStatistic(){
            List<EventDataRow> dataList = storage.get(EventType.SELECTED_VIDEOS);

            VideoSelectedEventDataRow row = new VideoSelectedEventDataRow(new ArrayList<Advertisement>(), 250, 50);

            Date date = null;
            Date date2 = null;
            Date date3 = null;
            try {
                date = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "14.05.2013" );
                date2 = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "13.05.2013" );
                date3 = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "12.05.2013" );
            } catch (ParseException e) {
            }
            row.setCurrentDate(date);
            dataList.add(row);

            row = new VideoSelectedEventDataRow(new ArrayList<Advertisement>(), 102, 50);
            row.setCurrentDate(date2);
            dataList.add(row);

            row = new VideoSelectedEventDataRow(new ArrayList<Advertisement>(), 54398, 50);
            row.setCurrentDate(date3);
            dataList.add(row);
        }
    }
}
