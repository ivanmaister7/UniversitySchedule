package com.schedule.proj.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

//@Component
@Entity
@Table(name="subject")
public class Subject {
    @JsonIgnore
    @ManyToMany(mappedBy = "subjectsList", cascade = CascadeType.ALL)
    private List<Student> studentsList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Teacher subjectTeacher;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="subject_id")
    private Long subjectId;

    @Size(min = 4, max = 200,
            message = "Lesson name must be between 4 and 32 characters")
    @Column(name="subject_name")
    private String subjectName;

    @NotNull
    @Column(name="day_of_week")
    private DayOfWeek dayOfWeek;

    @NotNull
    @Column(name="subject_time")
    private LocalTime subjectTime;

    @NotNull
    @ElementCollection
    private Collection<Integer> weeks;

    @NotNull
    @Column(name="subject_group")
    private Integer subjectGroup;

    @NotNull
    @Column(name="subject_faculty")
    private String subjectFaculty;

    @NotNull
    @Column(name="subject_speciality")
    private String subjectSpeciality;

    @NotNull
    @Column(name="education_format")
    private String educationFormat;

    public Long getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String lessonName) {
        this.subjectName = lessonName;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getSubjectTime() {
        return subjectTime;
    }

    public void setSubjectTime(LocalTime lessonTime) {
        this.subjectTime = lessonTime;
    }

    public Integer getSubjectGroup() {
        return subjectGroup;
    }

    public void setSubjectGroup(int lessonGroup) {
        this.subjectGroup = lessonGroup;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public Teacher getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(Teacher teacher) {
        this.subjectTeacher = teacher;
    }

    public Collection<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(Collection<Integer> weeks) {
        this.weeks = weeks;
    }

    public void setLessonGroup(Integer lessonGroup) {
        this.subjectGroup = lessonGroup;
    }

    public void setSubjectGroup(Integer subjectGroup) {
        this.subjectGroup = subjectGroup;
    }

    public String getSubjectFaculty() {
        return subjectFaculty;
    }

    public void setSubjectFaculty(String subjectFaculty) {
        this.subjectFaculty = subjectFaculty;
    }

    public String getSubjectSpeciality() {
        return subjectSpeciality;
    }

    public void setSubjectSpeciality(String subjectSpeciality) {
        this.subjectSpeciality = subjectSpeciality;
    }

    public String getEducationFormat() {
        return educationFormat;
    }

    public void setEducationFormat(String educationFormat) {
        this.educationFormat = educationFormat;
    }

    public Subject(Teacher subjectTeacher, String subjectName, DayOfWeek dayOfWeek,
                   LocalTime subjectTime, Integer subjectGroup, String subjectFaculty,
                   String subjectSpeciality, String educationFormat) {
        this.subjectTeacher = subjectTeacher;
        this.subjectName = subjectName;
        this.dayOfWeek = dayOfWeek;
        this.subjectTime = subjectTime;
        this.subjectGroup = subjectGroup;
        this.subjectFaculty = subjectFaculty;
        this.subjectSpeciality = subjectSpeciality;
        this.educationFormat = educationFormat;
    }

    public Subject() {}
}
