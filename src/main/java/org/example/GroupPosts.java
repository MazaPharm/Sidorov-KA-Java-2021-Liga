package org.example;


import javax.persistence.*;

@Entity
@Table(name = "groupPosts")
public class GroupPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups groups_id;
}
