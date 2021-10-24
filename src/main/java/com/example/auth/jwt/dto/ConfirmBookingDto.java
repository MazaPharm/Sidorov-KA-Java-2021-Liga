package com.example.auth.jwt.dto;

import com.example.auth.jwt.entity.ConfirmBookingArrival;

public class ConfirmBookingDto {

    private Long id;
    private String date;
    private String userName;

    public ConfirmBookingDto(ConfirmBookingArrival confirmBookingArrival) {
        this.id = confirmBookingArrival.getId();
        this.date = confirmBookingArrival.getDate().toString();
        this.userName = confirmBookingArrival.getUser().getName();
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }


}
