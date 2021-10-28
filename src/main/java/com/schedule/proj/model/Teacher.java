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
@DiscriminatorValue("3")
public class Teacher extends User {
    @NotNull
    @NotEmpty
    private String faculty;

    @NotNull
    @NotEmpty
    private String cathedra;

    @NotEmpty
    private String rank;

    @OneToMany(mappedBy = "subjectTeacher", cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accounts_id")
    private Accounts accounts;

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

    public Accounts getTeacherAccounts() {
        return accounts;
    }

    public void setTeacherAccounts(Accounts teacherAccounts) {
        this.accounts = teacherAccounts;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Teacher(UserRole userRole, String email, String password, String firstName, String lastName,
                   String avatar, String faculty, String cathedra, String rank) {
        super(userRole, email, password, firstName, lastName, avatar);
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.rank = rank;
    }
}
