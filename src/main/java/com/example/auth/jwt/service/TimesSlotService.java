package com.example.auth.jwt.service;

import com.example.auth.jwt.entity.Booking;
import com.example.auth.jwt.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Сервис для проверки срока действия броеей
 */
@Service
public class TimesSlotService {


    private final BookingRepository bookingRepository;

    @Autowired
    public TimesSlotService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Метод через каждые 2 минуты проверяет брони в таблице Booking и Temporary
     * Если время которое забронировал пользователь прошло и его бронь не была переведна администратором
     * в активную, то она автоматически удаляется из Booking.
     * Так же проверяет временное хранилище, не подтвержденных пользователем броней,
     * на случай если бронь была сделана менее, чем за 15 минут до срока брони, чтобы не ждать 15 минт и
     * убрать бронь после истечения срока времени в брони.
     */
    @Scheduled(fixedDelay = 120000)
    private void checkOverdueSlot() {
        Date date = new Date();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                checkTemporary(date);
            }
        };
        Thread newThread = new Thread(task);
        newThread.start();
        List<Booking> bookingList = bookingRepository.findByStatus("ACCEPTED");
        for (Booking booking : bookingList) {
            Date bookingDate = booking.getDate();
            if (date.getTime() > bookingDate.getTime()) {
                bookingRepository.deleteById(booking.getId());
            }
        }
    }

    private void checkTemporary(Date date) {
        List<Booking> temporaryBookings = bookingRepository.findByStatus("WAIT");
        for (Booking temporaryBooking : temporaryBookings) {
            Date temporaryDate = temporaryBooking.getDate();
            if (date.getTime() > temporaryDate.getTime()) {
                bookingRepository.deleteById(temporaryBooking.getId());
            }
        }
    }
}
