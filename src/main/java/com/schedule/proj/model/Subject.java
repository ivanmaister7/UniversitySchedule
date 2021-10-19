package com.schedule.proj.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//@Component
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
//    @Column(name = "id", nullable = false)
    @Column(name="lesson_name")
    private String lessonName;

    @Column(name="day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name="lesson_time")
    private LocalTime lessonTime;
    private int weeks;

    @Column(name="lesson_group")
    private int lessonGroup;

    public int getId() {
    @JsonIgnore
    @ManyToMany(mappedBy = "student")
    private List<Student> studentsList;

    @JsonIgnore
    @ManyToMany(mappedBy = "teacher")
    private List<Teacher> teachersList;

    public Long getId() {
        return id;
    }

    //    public Lesson(String name, DayOfWeek dayOfWeek, LocalTime time, ArrayList<Integer> weeks, int group) {
//        this.name = name;
//        this.dayOfWeek = dayOfWeek;
//        this.time = time;
//        this.weeks = weeks;
//        this.group = group;
//    }\


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

    public int getLessonGroup() {
        return lessonGroup;
    }

    public void setLessonGroup(int lessonGroup) {
        this.lessonGroup = lessonGroup;
    }
}
