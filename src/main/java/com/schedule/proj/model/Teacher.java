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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private int teacherId;
    private String email;
    private String faculty;
    private String cathedra;
    private String rank;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accounts_id")
//    @MapsId
    private Accounts teacherAccounts;

    @Column(name="subjects_list")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Subject> subjectsList;

    public Teacher() {

    }

    public int getTeacherId() {
        return teacherId;
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

    public Accounts getTeacherAccounts() {
        return teacherAccounts;
    }

    public void setTeacherAccounts(Accounts teacherAccounts) {
        this.teacherAccounts = teacherAccounts;
    }

    public Set<Subject> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(Set<Subject> subjectsList) {
        this.subjectsList = subjectsList;
    }

    public Teacher(String email, String faculty, String cathedra, String rank) {
        this.email = email;
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.rank = rank;
    }
}