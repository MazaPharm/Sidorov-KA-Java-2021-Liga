package com.example.auth.jwt.service;

import com.example.auth.jwt.entity.Booking;
import com.example.auth.jwt.repository.BookingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Сервис по проверки подтверждения брони пользователем
 */
@Service
public class TemporaryStorageService {
    private final long TIME_TO_CONFIRM_BY_USER = 840000;
    private final BookingRepository bookingRepository;

    public TemporaryStorageService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Метод производит работу каждую минуту
     * берет лист сущностей из таблицы временного хранилища
     * берет дату боавления каждой сущности в таюлицу и если разница между текущем временем и временим добавления
     * сущности > 15 значит пользователь не подтвердил бронь и она убирается из временного хранилища
     * и выкдывается во вновь активные
     *
     * @throws ParseException обработка исключения для метода parse()
     */
    @Scheduled(fixedDelay = 60000)
    private void checkAccept() throws ParseException {
        List<Booking> temporaryBookings = bookingRepository.findByStatus("WAIT");
        Date date = new Date();
        for (Booking temporaryBooking : temporaryBookings) {
            if (date.getTime() - temporaryBooking.getSettingDate().getTime() > TIME_TO_CONFIRM_BY_USER) {
                bookingRepository.deleteById(temporaryBooking.getId());
            }
        }
    }
}
