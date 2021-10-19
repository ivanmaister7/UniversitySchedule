package com.schedule.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    private int id;
    public String faculty;
    public String cathedra;
    public String rank;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Accounts accounts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjectsList;

    public Teacher() {

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

    public Teacher(String faculty, String cathedra, String rank) {
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.rank = rank;
    }




}