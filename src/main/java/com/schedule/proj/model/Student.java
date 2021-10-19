package com.schedule.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "student")
public class Student {

    @Id
    private int id;

    private String faculty;

    private String speciality;
    private int year;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Accounts accounts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjectsList;

    public Student(String faculty, String speciality, int year) {
        this.faculty = faculty;
        this.speciality = speciality;
        this.year = year;
    }

    public Student() {

    }
    public void setId(int id) {
        this.id = id;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setYear(int year) {
        this.year = year;
    }



    public int getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getYear() {
        return year;
    }
}