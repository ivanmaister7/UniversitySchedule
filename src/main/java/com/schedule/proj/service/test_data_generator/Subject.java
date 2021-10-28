package com.schedule.proj.service.test_data_generator;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.Set;

public class Subject {
    private String name;
    private Teacher teacher;
    private String time;
    private Integer group;
    private Set<Integer> weeks;
    private String speciality;
    private String educationFormat;
    private String faculty;
    private DayOfWeek dayOfWeek;

    public Subject(String name, Teacher teacher, String time, Integer group, DayOfWeek dayOfWeek,
                   Set<Integer> weeks, String speciality, String educationFormat, String faculty) {
        this.name = name.replace("'", "''").replace("`", "''");
        this.teacher = teacher;
        this.time = time;
        this.group = group;
        this.dayOfWeek = dayOfWeek;
        this.weeks = weeks;
        this.speciality = speciality.replace("'", "''").replace("`", "''");
        this.educationFormat = educationFormat.replace("'", "''").replace("`", "''");
        this.faculty = faculty.replace("'", "''").replace("`", "''");
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Set<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(Set<Integer> weeks) {
        this.weeks = weeks;
    }

    public String getEducationFormat() {
        return educationFormat;
    }

    public void setEducationFormat(String educationFormat) {
        this.educationFormat = educationFormat;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "'" + name + "'\n" +
                "time: '" + time + "'\n" +
                "dayOfWeek: " + dayOfWeek +
                "teacher: '" + teacher + "'\n" +
                "group: " + group + "'\n" +
                "weeks: " + weeks + "'\n" +
                "educationFormat: '" + educationFormat + "'\n" +
                "faculty: '" + faculty + "'\n" +
                "speciality: '" + speciality + "'\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return name.equals(subject.name) && Objects.equals(teacher, subject.teacher) && Objects.equals(time, subject.time) && Objects.equals(group, subject.group) && Objects.equals(speciality, subject.speciality) && Objects.equals(educationFormat, subject.educationFormat) && Objects.equals(faculty, subject.faculty) && dayOfWeek == subject.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teacher, time, group, speciality, educationFormat, faculty, dayOfWeek);
    }
}
