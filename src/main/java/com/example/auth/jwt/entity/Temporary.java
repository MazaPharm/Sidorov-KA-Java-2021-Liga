package com.example.auth.jwt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "temporary_storage")
public class Temporary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "add_time")
    private Date date;

    @Column(name = "time_added")
    private Date timeAdded;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
