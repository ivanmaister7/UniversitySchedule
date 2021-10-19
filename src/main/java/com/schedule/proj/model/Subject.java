package com.schedule.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name="subject")
public class Subject {


    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int lessonId;
    private String name;
    private int week;
    private DayOfWeek dayOfWeek;
    private LocalTime time;
    private int weeks;
    private int group;

    @JsonIgnore
    @ManyToMany(mappedBy = "student")
    private List<Student> studentsList;

    @JsonIgnore
    @ManyToMany(mappedBy = "teacher")
    private List<Teacher> teachersList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


//    public Lesson(String name, DayOfWeek dayOfWeek, LocalTime time, ArrayList<Integer> weeks, int group) {
//        this.name = name;
//        this.dayOfWeek = dayOfWeek;
//        this.time = time;
//        this.weeks = weeks;
//        this.group = group;
//    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
