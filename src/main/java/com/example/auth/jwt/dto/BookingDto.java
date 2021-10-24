package com.example.auth.jwt.dto;

import com.example.auth.jwt.entity.Booking;

public class BookingDto {
    private Long id;
    private String date;

    public BookingDto(Booking booking) {
        this.id = booking.getId();
        this.date = booking.getDate().toString();
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}
