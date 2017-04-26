package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ArchMage on 25.04.17.
 */
public class DirectorTablet {

    public void printAdvertisementProfit(){
        Map<Date, Long> amountPerDay = StatisticManager.getInstance().getAmountPerDay();
        Double totalValue = 0d;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (Map.Entry<Date, Long> entry : amountPerDay.entrySet()){
            Double amount = ((double) entry.getValue()) / 100;
            Double roundedAmount = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            ConsoleHelper.writeMessage(dateFormat.format(entry.getKey()) + " - " + String.format("%.2f", roundedAmount).replaceAll(",", "."));
            totalValue += roundedAmount;
        }
        ConsoleHelper.writeMessage("Total - " + String.format("%.2f", totalValue).replaceAll(",", "."));
    }
    public void printCookWorkloading(){
        Map<Date, Map<String, Long>> cookStatistic = StatisticManager.getInstance().getCookStatistic();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        boolean isFirstDisplay = true;
        for (Map.Entry<Date, Map<String, Long>> entry : cookStatistic.entrySet()){
            if (isFirstDisplay) {
                ConsoleHelper.writeMessage(dateFormat.format(entry.getKey()));
                isFirstDisplay = false;
            } else {
                ConsoleHelper.writeMessage("");
                ConsoleHelper.writeMessage(dateFormat.format(entry.getKey()));
            }
            for (Map.Entry<String, Long> entryInList : entry.getValue().entrySet()){
                ConsoleHelper.writeMessage(String.format("%s - %d min", entryInList.getKey(), (entryInList.getValue() / 60)));
            }
        }
    }
    public void printActiveVideoSet(){

    }
    public void printArchivedVideoSet(){

    }
}
