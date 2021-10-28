package com.schedule.proj.service.test_data_generator;

import java.util.Objects;

public class Teacher {
    private String name;
    private String faculty;
    private String cathedra;
    private String rank;

    public Teacher(String name, String faculty, String rank) {
        this.name = name.replace("'", "''").replace("`", "''");
        this.faculty = faculty.replace("'", "''").replace("`", "''");
        this.rank = rank.replace("'", "''").replace("`", "''");
    }

    public Teacher(String name, String faculty, String cathedra, String rank) {
        this.name = name.replace("'", "''").replace("`", "''");
        this.faculty = faculty.replace("'", "''").replace("`", "''");
        this.cathedra = cathedra.replace("'", "''").replace("`", "''");
        this.rank = rank.replace("'", "''").replace("`", "''");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCathedra() {
        return cathedra;
    }

    public void setCathedra(String cathedra) {
        this.cathedra = cathedra;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", cathedra='" + cathedra + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return name.equals(teacher.name) && Objects.equals(faculty, teacher.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, faculty);
    }
}
