package com.schedule.proj.service;

import com.schedule.proj.exeption.TeacherNotFoundException;
import com.schedule.proj.model.Role;
import com.schedule.proj.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.schedule.proj.repository.TeacherRepository;
import com.schedule.proj.repository.UserRepository;


import java.util.List;

@Transactional
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository, UserService userService) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public List<Teacher> getFullInfoAllTeacher(){

        int theMentors = userRepository.findByUserRole(Role.TEACHER).size();
        if(theMentors!=0){
           return teacherRepository.findAll();
        }
        throw new TeacherNotFoundException("Teachers not found");

    }
}
