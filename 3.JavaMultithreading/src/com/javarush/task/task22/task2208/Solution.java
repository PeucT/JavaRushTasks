package com.javarush.task.task22.task2208;

import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder strB = new StringBuilder();
        for (Map.Entry<String, String> entry: params.entrySet()) {
            if (entry.getValue() != null) {
                if (!"".equals(strB.toString())) {
                    strB.append(" and ");
                }
                strB.append(entry.getKey());
                strB.append(" = '");
                strB.append(entry.getValue());
                strB.append("'");
            }
        }
        return strB.toString();
    }
}
