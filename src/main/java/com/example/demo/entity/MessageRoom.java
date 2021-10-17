package com.example.demo.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "massage_room")
public class MessageRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_user_id")
    private User firstUser;

    @ManyToOne
    @JoinColumn(name = "second_user_id")
    private User secondUser;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "messageRoom",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
