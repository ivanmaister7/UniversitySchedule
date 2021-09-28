package com.tablefi.model;


import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

@Component
public class Lesson {
    private int lessonId;
    private String name;
    private DayOfWeek dayOfWeek;
    private LocalTime time;
    private ArrayList<Integer> weeks;
    private int group;

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

    public ArrayList<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(ArrayList<Integer> weeks) {
        this.weeks = weeks;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
