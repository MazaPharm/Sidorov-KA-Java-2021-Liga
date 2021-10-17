package com.example.demo.dto;

import com.example.demo.entity.Friend;

public class FriendDto {

    private Long id;
    private String name;

    public FriendDto(Friend friend){
        this.id=friend.getFriend().getId();
        this.name = friend.getFriend().getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
