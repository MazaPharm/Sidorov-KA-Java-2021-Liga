package com.example.auth.jwt.service;


import com.example.auth.jwt.dto.BookingDto;
import com.example.auth.jwt.dto.UserDto;
import com.example.auth.jwt.entity.Booking;
import com.example.auth.jwt.entity.Roles;
import com.example.auth.jwt.entity.User;
import com.example.auth.jwt.repository.BookingRepository;
import com.example.auth.jwt.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserDetailsService {
    private final UserServiceSlots userServiceSlots;
    List<String> timeSlots = new ArrayList<>();
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookingRepository bookingRepository;

    public UserServiceImpl(UserServiceSlots userServiceSlots, UserRepository userRepository, PasswordEncoder passwordEncoder,
                           BookingRepository bookingRepository) {
        this.userServiceSlots = userServiceSlots;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookingRepository = bookingRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getAuthority())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    /**
     * Метод регистрирует пользователя в системе
     *
     * @param user экземпляр класса User
     * @return DTO класса User
     */
    @Transactional
    public UserDto saveUser(User user) {
        List<User> users = userRepository.findAll();
        if (users.size() <= 0) {
            user.setRoles(Collections.singleton(Roles.ADMIN));
        } else {
            user.setRoles(Collections.singleton(Roles.USER));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new UserDto(user);

    }

    /**
     * Метод позволяет просмотреть информацию по пользователю
     *
     * @return DTO класса User
     */
    public UserDto userInfo() {
        return new UserDto(getUser());
    }

    private Booking getBooking(Long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.get();
    }

    /**
     * Метод для быстрого получения экземпляр класса User из базы данных
     *
     * @return экземпляр класса User
     */
    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        return userRepository.findByUsername(currentUser);
    }
    /**
     * Метод позволяющий забронировать свободный слот
     *
     * @param slotId идентификатор слота
     * @return строку с информацией о том, что для подверждения брони необходимо перейти по ссылке,
     * при этом слот записывается во временное хранилище, до подтверждения или истечения 15 минут
     * или информацию о том, что выбранной время уже прошло и надо выбрать другое
     * @throws ParseException обработка исключения для метода parse()
     */
    @Transactional
    public String acceptBooking(int slotId) throws ParseException {
        Date date = new Date();
        String[] dates = timeSlots.get(slotId - 1).split("-");
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date setingDate = formatter.parse(dates[1]);
        if (date.getTime() > setingDate.getTime()) {
            return "This time has already passed! Change other time slot";
        }
        User user = getUser();
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSettingDate(date);
        booking.setDate(setingDate);
        booking.setStatus("WAIT");
        bookingRepository.save(booking);
        return "For accepted go to the link:" +
                "localhost:8080/user/accept/booking/?id=" + booking.getId();
    }

    /**
     * Метод, который подтверждает бронь от пользователя (пользователь перешел по ссылке)
     * и сохранение брони в таблицу
     *
     *
     */
    @Transactional
    public void bookingAccepted(Long bookingId) {
        Booking booking = getBooking(bookingId);
        booking.setStatus("ACCEPTED");
        bookingRepository.save(booking);
    }

    /**
     * Метод для просмотра активных броней пользователя
     *
     * @return DTO класса Booking
     */
    public List<BookingDto> userBooking() {
        List<Booking> bookings = bookingRepository.findByUserId(getUser().getId());
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDtos.add(new BookingDto(booking));
        }
        return bookingDtos;
    }

    /**
     * Отмена брони от пользователя
     *
     * @param bookingId идентификатор брони
     */
    @Transactional
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    /**
     * Просмотр и генерация свободных слотов, построенных по определенным правилоам
     *
     * @return список List свободных слотов на дни с понедельника по пятницу
     * @throws ParseException обработка исключения для метода parse()
     */
    public List<String> freeSlots() throws ParseException {
        return timeSlots = userServiceSlots.freeSlots();
    }
}
