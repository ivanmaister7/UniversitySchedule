package com.tablefi.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Teacher implements User {

    private Table table;
    public int teacherId;
    public String name;
    public String faculty;
    public String cathedra;
    public String rank;
    public String email;

    public Teacher(String name, String faculty, String cathedra, String rank, String email) {
        this.name = name;
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.rank = rank;
        this.email = email;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Autowired
    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public void showDaySchedule() {

    }

    @Override
    public void showWeekSchedule() {

    }
}
