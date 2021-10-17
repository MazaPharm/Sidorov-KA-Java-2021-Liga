package com.example.demo.dto;

import com.example.demo.entity.Message;

public class MessageDto {

    private Long id;
    private String name;
    private String text;

    public MessageDto(Message message){
        this.id=message.getId();
        this.name=message.getUser().getName();
        this.text=message.getText();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
