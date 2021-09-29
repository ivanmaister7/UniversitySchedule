package com.schedule.proj.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Table {
    List<Lesson> lessonList;

    @Autowired
    public Table(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

//    @Autowired
//    public void setLessonList(List<Lesson> lessonList) {
//        this.lessonList = lessonList;
//    }
}
