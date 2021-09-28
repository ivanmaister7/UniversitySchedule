package com.tablefi.model;


import org.springframework.beans.factory.annotation.Autowired;

public class Student implements User{

    private Table table;
    private int studentId;
    private String name;
    private String faculty;
    private String speciality;
    private int year;
    // the same as login
    private String email;

    public Student(String name, String faculty, String speciality, int year, String email) {
        this.name = name;
        this.faculty = faculty;
        this.speciality = speciality;
        this.year = year;
        this.email = email;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
