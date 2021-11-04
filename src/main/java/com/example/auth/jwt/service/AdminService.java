package com.example.auth.jwt.service;

import com.example.auth.jwt.dto.BookingDto;
import com.example.auth.jwt.dto.UserDto;
import com.example.auth.jwt.entity.Booking;
import com.example.auth.jwt.entity.Roles;
import com.example.auth.jwt.entity.User;
import com.example.auth.jwt.repository.BookingRepository;
import com.example.auth.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AdminService {

    private boolean inProcess = false;
    private Booking confirmBookingArrivalInProcess;
    private List<Booking> sortedByDate = new ArrayList<>();
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public AdminService(UserRepository userRepository,
                        BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Метод возвращает DTO списо пользователей
     *
     * @return список зарегестрированных аользователей
     */
    public List<UserDto> allUsers() {
        List<User> users = userRepository.findAll();
        users.removeIf(user -> user.getId().equals(getAdmin().getId()));
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user));
        }
        return userDtos;
    }

    /**
     * Метод для быстрого получения User
     *
     * @return Возвращает сущность класса User, конкретно админа
     */
    private User getAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        return userRepository.findByUsername(currentUser);
    }

    /**
     * Метод для быстрого получения User
     *
     * @return Возвращает сущность класса User, пользователя по id
     */
    private User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    /**
     * Метод для быстрого получения сущности Booking
     *
     * @param id резерва
     * @return экземпляр класса Booking
     */
    private Booking getBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.get();
    }

    /**
     * Метож удаляет пользователя из системы
     *
     * @param userId идентификатор пользователя
     */
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Метод для получения резерваций, которые подтвердил пользователь
     *
     * @return список активных броней
     */
    public List<BookingDto> allBooking() {
        List<Booking> bookings = bookingRepository.findByStatus("ACCEPTED");
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDtos.add(new BookingDto(booking));
        }
        return bookingDtos;
    }

    /**
     * Метод, который подтверждает факт явки пользователя заблаговременно
     *
     * @param bookingId идентификатор брони
     */
    @Transactional
    public void confirmBookingArrival(Long bookingId) {
        Booking booking = getBooking(bookingId);
        booking.setStatus("CONFIRMED");
        sortByDate();
    }

    /**
     * Метод вызывает слеующего по времени пришедшего пользователя.
     * Если предыдущая бронь была помечена, как выполненная
     *
     * @return строку с информацией о следующем пользователе, который может пойти на прием
     * или сообщение о том, что больше пользователей нет или предыдущий слот не был закрыт
     */
    public String nextVisitor() {
        if (sortedByDate.size() == 0 || inProcess) {
            return "No person for now or you did not end previous slot";
        }
        inProcess = true;
        confirmBookingArrivalInProcess = sortedByDate.get(0);
        return confirmBookingArrivalInProcess.getUser().getName() + " with time " +
                confirmBookingArrivalInProcess.getDate() + " you can go";
    }

    /**
     * Метод, закрывающий заявку, как исполненную
     *
     * @return информацию, что можно пригласить следующего пользователя
     */
    @Transactional
    public String endSlot() {
        inProcess = false;
        bookingRepository.deleteById(confirmBookingArrivalInProcess.getId());
        sortedByDate.remove(confirmBookingArrivalInProcess);
        confirmBookingArrivalInProcess = null;
        return "You can call next";
    }

    /**
     * Просмотр слота, который сейчас на приеме
     *
     * @return DTO текущего выполняемого слота
     */
    public BookingDto currentBookingInProcess() {
        if (confirmBookingArrivalInProcess != null) {
            return new BookingDto(confirmBookingArrivalInProcess);
        }
        return null;
    }

    /**
     * Метод позволяет просмотреть следующего по времени пользователя, из пришедших
     *
     * @return строку с информацией о имене пользователя и его времени или
     * информаци. о том, что дальше нет пользователей
     */
    public String seeNextVisitor() {
        if (sortedByDate.size() > 1 && inProcess) {
            Booking nextVisitorByTime = sortedByDate.get(1);
            return " Next by time:" + nextVisitorByTime.getUser().getName() + "  time slot " + nextVisitorByTime.getDate();
        } else if (sortedByDate.size() >= 1) {
            Booking nextVisitorByTime = sortedByDate.get(0);
            return " Next by time:" + nextVisitorByTime.getUser().getName() + " with time slot " + nextVisitorByTime.getDate();
        }
        return "No more visitors";
    }

    /**
     * Метод который сортирует лист пришедших пользователей по дате, и конвертит их в List<ConfirmBookingArrival>
     */
    private void sortByDate() {
        List<Booking> confirmBookingArrivals = bookingRepository.findByStatus("CONFIRMED");
        sortedByDate = confirmBookingArrivals.stream()
                .sorted(Comparator.comparing(Booking::getDate)).collect(Collectors.toList());
    }

    /**
     * Метод делает из пользователя администратора
     *
     * @param userId идентификатор пользователя, которого надо сделать администратором
     */
    @Transactional
    public void makeAdmin(Long userId) {
        User user = new User();
        User oldUser = getUser(userId);
        user.setName(oldUser.getName());
        user.setUsername(oldUser.getUsername());
        user.setRoles(Collections.singleton(Roles.ADMIN));
        user.setPassword(oldUser.getPassword());
        userRepository.deleteById(userId);
        userRepository.save(user);
    }

    /**
     * Метод возвращает список активных броней, которые админ отметил, как явившиеся на прием
     * @return DTO класса ConfirmBookingDto
     */
    public List<BookingDto> getListConfirmBookingByAdmin() {
        List<Booking> confirmBookingArrivals = bookingRepository.findByStatus("CONFIRMED");
        List<BookingDto> confirmBookingDtos = new ArrayList<>();
        for (Booking confirmBookingArrival:confirmBookingArrivals) {
            confirmBookingDtos.add(new BookingDto(confirmBookingArrival));
        }
        return confirmBookingDtos;
    }
}
