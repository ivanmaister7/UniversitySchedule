package com.schedule.proj.controller;

import com.schedule.proj.model.Teacher;
import com.schedule.proj.service.SubjectService;
import com.schedule.proj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final SubjectService subjectService;

    @Autowired
    public TeacherController(TeacherService teacherService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @GetMapping()
    @ResponseBody
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(path = "{teacherId}")
    @ResponseBody
    public Teacher getTeacher(@PathVariable Long teacherId) {
        return teacherService.getTeacher(teacherId);
    }

    @PostMapping(consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public Teacher addTeacher(@RequestBody @Valid Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @PutMapping(consumes = "application/json",
            produces = "application/json")
    public Teacher updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @DeleteMapping(path = "{teacherId}")
    public void deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
    }

    @PutMapping("{teacherId}/addSubject")
    public void addSubject(@PathVariable Long teacherId,
                           @RequestParam Long subjectId) {
        subjectService.setTeacher(subjectId, teacherId);
    }

}
