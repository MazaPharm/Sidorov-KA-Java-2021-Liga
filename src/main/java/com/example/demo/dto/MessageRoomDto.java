package com.example.demo.dto;

import com.example.demo.entity.MessageRoom;

public class MessageRoomDto {

    private String firstUserName;
    private String secondUserName;
    private Long id;

    public MessageRoomDto(MessageRoom messageRoom){
        this.id = messageRoom.getId();
        this.firstUserName = messageRoom.getFirstUser().getName();
        this.secondUserName = messageRoom.getSecondUser().getName();
    }

    public String getFirstUserName() {
        return firstUserName;
    }

    public String getSecondUserName() {
        return secondUserName;
    }

    public Long getId() {
        return id;
    }
}
