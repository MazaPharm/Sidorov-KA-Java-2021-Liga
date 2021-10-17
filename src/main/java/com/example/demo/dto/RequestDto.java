package com.example.demo.dto;

import com.example.demo.entity.FriendRequest;

public class RequestDto {

    private String userName;
    private Long id;

    public RequestDto(FriendRequest friendRequest) {
        this.userName = friendRequest.getUser().getName();
        this.id = friendRequest.getId();
    }

    public String getUserName() {
        return userName;
    }

    public Long getId() {
        return id;
    }
}
