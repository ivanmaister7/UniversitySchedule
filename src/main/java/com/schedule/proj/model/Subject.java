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
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher subjectTeacher;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="subject_id")
    private Long subjectId;

    @Size(min = 4, max = 32,
            message = "Lesson name must be between 4 and 32 characters")
    @Column(name="lesson_name")
    private String lessonName;

    @NotNull
    @Column(name="day_of_week")
    private DayOfWeek dayOfWeek;

    @NotNull
    @Column(name="lesson_time")
    private LocalTime lessonTime;

    @NotNull
    @ElementCollection
    private Collection<Integer> weeks;

    @NotNull
    @Column(name="lesson_group")
    private Integer lessonGroup;

    public Long getSubjectId() {
        return subjectId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(LocalTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Integer getLessonGroup() {
        return lessonGroup;
    }

    public void setLessonGroup(int lessonGroup) {
        this.lessonGroup = lessonGroup;
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
        this.lessonGroup = lessonGroup;
    }
}
