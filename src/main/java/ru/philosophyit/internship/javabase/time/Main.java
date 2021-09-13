package ru.philosophyit.internship.javabase.time;

import java.text.DateFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main {

    private static StringBuilder days;
    private static String daysOfWeek, tableDays;

    public static void main(String... args) throws Exception {

        System.out.println(DateTimeFormatter.ISO_INSTANT.format(Calendar.getInstance().toInstant()));

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                nameOfDays();
            }
        });

       Thread t2 = new Thread(new Runnable() {
           @Override
           public void run() {
               daysOfMonth();
           }
       });
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
        StringBuilder dayOfWeks = new StringBuilder();
        for (int i = 2; i < namesOfDays.length; i++) {
            dayOfWeks.append(namesOfDays[i]);
            dayOfWeks.append(" ");
        }
        dayOfWeks.append(namesOfDays[1]);
      daysOfWeek=dayOfWeks.toString();
    }

    private static void daysOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int countDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(2021, month, 1);
        int dayInBegin = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(2021, month - 1, 1);
        int daysInPreviousMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        tableDays=tableOfDays(countDays, dayInBegin, daysInPreviousMonth);
    }

    private static String tableOfDays(int countDays, int dayInBegin, int daysInPreviousMonth) {
        days = new StringBuilder();
        int d = 0;
        int prvsMonth = 0;
        if (dayInBegin == 1) {
            prvsMonth = 6;
        } else
            prvsMonth = (7 - (7 - dayInBegin + 1)) - 1;
        int k = 1;
        int limit = 35 - prvsMonth;
        while (k <= limit) {
            while (prvsMonth > 0) {
                days.append((31 - prvsMonth) + 1);
                d++;
                days.append(" ");
                prvsMonth--;
                if (d == 7) {
                    d = 0;
                    days.append("\n");
                }
            }
            days.append(k);
            d++;
            days.append(" ");
            if (k < 10) days.append(" ");
            if (d == 7) {
                d = 0;
                days.append("\n");
            }
            if (k >= countDays) {
                k = 1;
                while (k <= (limit - countDays)) {
                    days.append(k);
                    d++;
                    days.append(" ");
                    days.append(" ");
                    if (d == 7) {
                        d = 0;
                        days.append("\n");
                    }
                    k++;
                }
                break;
            }
            k++;
        }
        return (days.toString());
    }
}
