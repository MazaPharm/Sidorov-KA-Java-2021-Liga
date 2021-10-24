package com.example.auth.jwt.dto;

import com.example.auth.jwt.entity.User;

public class UserDto {

    private Long id;
    private String name;
    private String username;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
