package com.example.auth.jwt.controller;

import com.example.auth.jwt.dto.BookingDto;
import com.example.auth.jwt.dto.UserDto;
import com.example.auth.jwt.entity.User;
import com.example.auth.jwt.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public UserDto registrationUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/info")
    public UserDto userInfo() {
        return userService.userInfo();
    }

    @GetMapping("/free/slots")
    public List<String> freeSlots() throws ParseException {
        return userService.freeSlots();
    }

    @PostMapping("/booking")
    public String bookingTime(@RequestParam("id") int slotId) throws ParseException {
        return userService.acceptBooking(slotId);
    }

    @PostMapping("/accept/booking")
    public void bookingAccepted(@RequestParam("id") Long temporaryId) {
        userService.bookingAccepted(temporaryId);
    }

    @GetMapping("/myBooking")
    public List<BookingDto> userBooking() {
        return userService.userBooking();
    }

    @PostMapping("/cancel/booking")
    public void cancelBooking(@RequestParam("id") Long bookingId) {
        userService.cancelBooking(bookingId);
    }
}
