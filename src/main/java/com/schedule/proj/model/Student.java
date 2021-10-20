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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    private String faculty;

    private String speciality;
    @Column(name = "student_year")
    private int studentYear;

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

    public int getStudentId() {
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

    public int getStudentYear() {
        return studentYear;
    }
}