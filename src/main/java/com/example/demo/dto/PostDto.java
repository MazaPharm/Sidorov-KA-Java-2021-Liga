package com.example.demo.dto;

import com.example.demo.entity.Post;

public class PostDto {

    private Long id;
    private String text;
    private String ownerName;

    public PostDto(Post post) {
        this.id = post.getId();
        this.text = post.getText();
        this.ownerName = post.getUser().getName();
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
