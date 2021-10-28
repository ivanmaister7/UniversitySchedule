package com.schedule.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private Long teacherId;

    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String faculty;

    @NotNull
    @NotEmpty
    private String cathedra;

    @NotEmpty
    private String rank;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accounts_id")
    private Accounts accounts;

    @OneToMany(mappedBy = "subjectTeacher", cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    public Teacher() {

    }

    public Long getTeacherId() {
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


    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Teacher(String email, String faculty, String cathedra, String rank) {
        this.email = email;
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.rank = rank;
    }
}
