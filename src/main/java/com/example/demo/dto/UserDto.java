package com.example.demo.dto;

import com.example.demo.entity.User;

public class UserDto {

    private Long id;
    private String login;
    private String name;

    public UserDto(User user) {
        this.login = user.getLogin();
        this.id=user.getId();
        this.name=user.getName();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

}
