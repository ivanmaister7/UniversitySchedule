package com.tablefi.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
