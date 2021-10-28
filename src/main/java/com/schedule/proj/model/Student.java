package com.schedule.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@DiscriminatorValue("2")
@DiscriminatorOptions(force=true)
public class Student extends User {
    @NotNull
    @NotEmpty
    private String faculty;

    @NotNull
    @NotEmpty
    private String speciality;

    @Min(value = 1, message = "Year must not be less than 1")
    @Max(value = 6, message = "Year must not be greater than 6")
    @Column(name = "student_year")
    private Integer studentYear;

    @Column(name="subjects_list")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Subject> subjectsList;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accounts_id")
    private Accounts accounts;

    public Student(UserRole userRole, String email, String password, String firstName, String lastName,
                   String avatar, String speciality, String faculty, Integer studentYear) {
        super(userRole, email, password, firstName, lastName, avatar);
        this.speciality = speciality;
        this.faculty = faculty;
        this.studentYear = studentYear;
    }

    public Student() {

    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Integer getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(Integer studentYear) {
        this.studentYear = studentYear;
    }

    public Accounts getStudentAccounts() {
        return accounts;
    }

    public void setStudentAccounts(Accounts studentAccounts) {
        this.accounts = studentAccounts;
    }

    public Set<Subject> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(Set<Subject> subjectsList) {
        this.subjectsList = subjectsList;
    }
}
