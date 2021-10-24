package com.example.auth.jwt.service;

import com.example.auth.jwt.entity.Booking;
import com.example.auth.jwt.entity.ConfirmBookingArrival;
import com.example.auth.jwt.entity.Temporary;
import com.example.auth.jwt.repository.BookingRepository;
import com.example.auth.jwt.repository.ConfirmBookingArrivalRepository;
import com.example.auth.jwt.repository.TemporaryRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Сервтс который создате список свобоных слотов по правилам
 */
@Service
public class UserServiceSlots {

    List<String> timeSlots = new ArrayList<>();
    private final BookingRepository bookingRepository;
    private final TemporaryRepository temporaryRepository;
    private final ConfirmBookingArrivalRepository confirmBookingArrivalRepository;

    public UserServiceSlots(BookingRepository bookingRepository, TemporaryRepository temporaryRepository,
                            ConfirmBookingArrivalRepository confirmBookingArrivalRepository) {
        this.bookingRepository = bookingRepository;
        this.temporaryRepository = temporaryRepository;
        this.confirmBookingArrivalRepository = confirmBookingArrivalRepository;
    }

    /**
     * Метод строит список свободных слотов по правилам
     * проверяет текущую дату, когда был сделан запрос, показывает дни
     * с понедельника по пятницу и по определенному времени.
     * Если запрос сделан в субботу или воскресенье показывются слоты с понедельника по пятницу
     * следующей недели
     * если запрос сделан в будний день, то отобразится список слотов начиная с этого дня и до пятницы
     *
     * @return List свободных слотов
     * @throws ParseException обработка исключения для метода parse()
     */
    public List<String> freeSlots() throws ParseException {
        timeSlots.clear();
        Calendar calendar = Calendar.getInstance();
        Calendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        int toLastWorkDay;
        int COUNT_OF_DAYS = 5;
        switch (gregorianCalendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SATURDAY:
                toLastWorkDay = COUNT_OF_DAYS;
                gregorianCalendar.add(Calendar.DAY_OF_MONTH, 2);
                break;
            case Calendar.SUNDAY:
                toLastWorkDay = COUNT_OF_DAYS;
                gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case Calendar.FRIDAY:
                toLastWorkDay = 1;
                break;
            default:
                toLastWorkDay = Math.abs(gregorianCalendar.get(Calendar.DAY_OF_WEEK) - COUNT_OF_DAYS) + 2;
                break;
        }
        while (toLastWorkDay > 0) {
            toLastWorkDay--;
            if (Calendar.DAY_OF_MONTH == 1) {
                gregorianCalendar.roll(Calendar.MONTH, +1);
            }
            addTime(gregorianCalendar);
            gregorianCalendar.roll(Calendar.DAY_OF_MONTH, +1);
        }
        return timeSlots;
    }

    /**
     * Метод добавляет временные промежутки
     *
     * @param gregorianCalendar Экземпляр календарь
     * @throws ParseException обработка исключения для метода parse()
     */
    private void addTime(Calendar gregorianCalendar) throws ParseException {
        List<String> time = new ArrayList<>();
        time.add("14:00");
        time.add("14:30");
        time.add("15:00");
        time.add("15:30");
        time.add("16:00");
        time.add("16:30");
        time.add("17:00");
        time.add("17:30");
        time.add("18:00");

        for (int i = 0; i < time.size(); i++) {
            String[] timeSplit = time.get(i).split(":");
            gregorianCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSplit[0]));
            gregorianCalendar.set(Calendar.MINUTE, Integer.parseInt(timeSplit[1]));
            ConfirmBookingArrival confirmBookingArrival = confirmBookingArrivalRepository
                    .findByDate(gregorianCalendar.getTime());
            Booking booking = bookingRepository.findByDate(gregorianCalendar.getTime());
            Temporary temporary = temporaryRepository.findByDate(gregorianCalendar.getTime());
            if (booking == null && temporary == null && confirmBookingArrival == null) {
                timeSlots.add((timeSlots.size() + 1) + "-" + gregorianCalendar.getTime().toString());
            }
        }

    }

}
