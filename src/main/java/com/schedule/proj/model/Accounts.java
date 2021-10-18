package com.schedule.proj.model;


import javax.persistence.*;

@Entity
//@Table(name="Accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    public Accounts() {
    }

    public Accounts(User user) {
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


  }
