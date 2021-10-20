package com.schedule.proj.model;


import javax.persistence.*;

@Entity
//@Table(name="Accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accounts_id")
    private int accountsId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @OneToOne (mappedBy = "teacherAccounts", cascade = CascadeType.PERSIST)
    private Teacher teacher;

    @OneToOne (mappedBy = "studentAccounts", cascade = CascadeType.REFRESH)
    private Student student;

    public Accounts() {
    }

    public Accounts(User user) {
        this.user = user;
    }


    public int getAccountsId() {
        return accountsId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


  }
