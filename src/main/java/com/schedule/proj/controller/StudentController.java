package com.schedule.proj.controller;

import com.schedule.proj.exсeption.InvalidStudentException;
import com.schedule.proj.model.Student;
import com.schedule.proj.repository.StudentRepository;
import com.schedule.proj.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "{studentId}")
    @ResponseBody
    public Student getStudent(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping(consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping(consumes = "application/json",
            produces = "application/json")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("{studentId}/addSubject")
    public void addSubject(@PathVariable Long studentId,
                           @RequestParam Long subjectId) {
        studentService.addSubject(studentId, subjectId);
    }
}
