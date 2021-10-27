package com.schedule.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

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

    @OneToOne
    @JoinColumn(name = "accounts_id")
//    @MapsId
    private Accounts studentAccounts;

    @Column(name="subjects_list")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Subject> subjectsList;

    public Student(String faculty, String speciality, int studentYear) {
        this.faculty = faculty;
        this.speciality = speciality;
        this.studentYear = studentYear;
    }

    public Student() {

    }

    public Long getStudentId() {
        return studentId;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setStudentYear(int studentYear) {
        this.studentYear = studentYear;
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
        return studentAccounts;
    }

    public void setStudentAccounts(Accounts studentAccounts) {
        this.studentAccounts = studentAccounts;
    }

    public Set<Subject> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(Set<Subject> subjectsList) {
        this.subjectsList = subjectsList;
    }
}
