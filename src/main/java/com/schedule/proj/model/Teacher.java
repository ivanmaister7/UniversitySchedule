package com.schedule.proj.model;

import javax.persistence.*;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    public String email;

    @Column(name="first_name")
    public String firstName;

    @Column(name="last_name")
    public String lastName;

    public String faculty;
    public String cathedra;
    public String rank;

    public Teacher() {

    }

    public int getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCathedra() {
        return cathedra;
    }

    public void setCathedra(String cathedra) {
        this.cathedra = cathedra;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Teacher(String faculty, String cathedra, String rank) {
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.rank = rank;
    }




}