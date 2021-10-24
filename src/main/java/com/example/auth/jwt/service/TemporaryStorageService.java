package com.example.auth.jwt.service;

import com.example.auth.jwt.entity.Temporary;
import com.example.auth.jwt.repository.TemporaryRepository;
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

    private final long TIME_TO_CONFIRM_BY_USER=840000;
    private final TemporaryRepository temporaryRepository;

    public TemporaryStorageService(TemporaryRepository temporaryRepository) {
        this.temporaryRepository = temporaryRepository;

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
        List<Temporary> temporaryList = temporaryRepository.findAll();
        Date date = new Date();
        for (Temporary temporary : temporaryList) {
            if (date.getTime() - temporary.getTimeAdded().getTime() > TIME_TO_CONFIRM_BY_USER) {
                temporaryRepository.deleteById(temporary.getId());
            }
        }
    }
}
