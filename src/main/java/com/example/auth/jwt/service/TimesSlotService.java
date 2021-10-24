package com.example.auth.jwt.service;

import com.example.auth.jwt.entity.Booking;
import com.example.auth.jwt.entity.Temporary;
import com.example.auth.jwt.repository.BookingRepository;
import com.example.auth.jwt.repository.TemporaryRepository;
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
    private final TemporaryRepository temporaryRepository;

    @Autowired
    public TimesSlotService(BookingRepository bookingRepository, TemporaryRepository temporaryRepository) {
        this.bookingRepository = bookingRepository;
        this.temporaryRepository = temporaryRepository;
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
        List<Booking> bookingList = bookingRepository.findAll();
        for (Booking booking : bookingList) {
            Date bookingDate = booking.getDate();
            if (date.getTime() > bookingDate.getTime()) {
                bookingRepository.deleteById(booking.getId());
            }
        }
    }

    private void checkTemporary(Date date) {
        List<Temporary> temporaries = temporaryRepository.findAll();
        for (Temporary temporary : temporaries) {
            Date temporaryDate = temporary.getDate();
            if (date.getTime() > temporaryDate.getTime()) {
                temporaryRepository.deleteById(temporary.getId());
            }
        }
    }
}
