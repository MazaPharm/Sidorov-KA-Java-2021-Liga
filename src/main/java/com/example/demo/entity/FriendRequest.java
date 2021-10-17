package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "friend_request")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
