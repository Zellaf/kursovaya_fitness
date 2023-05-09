package com.fitness.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<UserCoach> userCoaches;

    public Coach() {

    }

    public Coach(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserCoach> getUserCoaches() {
        return userCoaches;
    }

    public void setUserCoaches(List<UserCoach> userCoaches) {
        this.userCoaches = userCoaches;
    }
}