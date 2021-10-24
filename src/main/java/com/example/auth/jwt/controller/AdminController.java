package com.example.auth.jwt.controller;

import com.example.auth.jwt.dto.BookingDto;
import com.example.auth.jwt.dto.ConfirmBookingDto;
import com.example.auth.jwt.dto.UserDto;
import com.example.auth.jwt.service.AdminService;
import com.example.auth.jwt.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final UserServiceImpl userService;

    @Autowired
    public AdminController(AdminService adminService, UserServiceImpl userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> allUsers() {
        return adminService.allUsers();
    }

    @PostMapping("/user/delete")
    public void deleteUser(@RequestParam("id") Long userId) {
        adminService.deleteUser(userId);
    }

    @GetMapping("/bookings")
    public List<BookingDto> allBooking() {
        return adminService.allBooking();
    }

    @PostMapping("/cancel/booking")
    public void cancelBooking(@RequestParam("id") Long bookingId) {
        userService.cancelBooking(bookingId);
    }

    @PostMapping("/confirm/booking")
    public void confirmBookingArrival(@RequestParam("id") Long bookingId) {
        adminService.confirmBookingArrival(bookingId);
    }

    @PostMapping("/next/visitor")
    public String nextVisitor() {
        return adminService.nextVisitor();
    }

    @GetMapping("/current/slot")
    public ConfirmBookingDto currentSlotInProcess() {
        return adminService.currentBookingInProcess();
    }

    @PostMapping("/end/current/slot")
    public String endSlot() {
        return adminService.endSlot();
    }

    @GetMapping("/see/next")
    public String seeNextVisitor() {
        return adminService.seeNextVisitor();
    }

    @PostMapping("/make/admin")
    public void makeUserAdmin(@RequestParam("id") Long userId) {
        adminService.makeAdmin(userId);
    }

    @GetMapping("/list/confirm/bookings")
    public List<ConfirmBookingDto> getListConfirmBookingByAdmin(){
         return adminService.getListConfirmBookingByAdmin();
    }

}
