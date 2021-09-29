package ru.philosophyit.internship.javabase.time;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static StringBuilder days;
    private static String daysOfWeek, tableDays;


    public static void main(String... args) throws Exception {

        System.out.println(DateTimeFormatter.ISO_INSTANT.format(Calendar.getInstance().toInstant()));

        Thread t1 = new Thread(()->nameOfDays());

       Thread t2 = new Thread(() -> daysOfMonth());
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        System.out.println(daysOfWeek);
        System.out.println(tableDays);

        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.
    }

    private static void nameOfDays() {
        String[] namesOfDays = DateFormatSymbols.getInstance().getShortWeekdays();
        daysOfWeek=Arrays.stream(namesOfDays,2,namesOfDays.length).collect(Collectors.joining(" "))
                +" "+namesOfDays[1];
    }

    private static void daysOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int countDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(2021, month, 1);
        int dayInBegin = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(2021, month - 1, 1);
        int daysInPreviousMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        tableDays=tableOfDays(countDays, dayInBegin, daysInPreviousMonth, month);
    }

    private static String tableOfDays(int countDays, int dayInBegin, int daysInPreviousMonth, int month) {
        SimpleDateFormat f = new SimpleDateFormat("dd");
        days = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int k=0;
        int dayBeforeCurrentMonth=(7 - (7 - dayInBegin + 1)) - 1;
        calendar.set(2021,month-1,(daysInPreviousMonth-dayBeforeCurrentMonth)+1);
        while(k<35){
            k++;
            days.append(f.format(calendar.getTime()));
            days.append(" ");
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            if(k%7==0)
                days.append("\n");
        }
        return (days.toString());
    }
}
