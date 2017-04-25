package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by ArchMage on 25.04.17.
 */
public class DirectorTablet {

    public void printAdvertisementProfit(){
        Map<Date, Long> amountPerDay = StatisticManager.getInstance().getAmountPerDay();
        Double totalValue = 0d;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (Map.Entry<Date, Long> entry : amountPerDay.entrySet()){
            Double amount = ((double) entry.getValue()) / 1000;
            Double roundedAmount = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            ConsoleHelper.writeMessage(dateFormat.format(entry.getKey()) + " - " + roundedAmount);
            totalValue += roundedAmount;
        }
        ConsoleHelper.writeMessage("Total - " + totalValue);
    }
    public void printCookWorkloading(){

    }
    public void printActiveVideoSet(){

    }
    public void printArchivedVideoSet(){

    }
}
