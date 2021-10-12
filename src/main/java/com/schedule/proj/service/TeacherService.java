package com.schedule.proj.service;

import com.schedule.proj.exeption.TeacherNotFoundException;
import com.schedule.proj.model.Role;
import com.schedule.proj.model.Teacher;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.schedule.proj.repository.TeacherRepository;
import com.schedule.proj.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public TeacherService(UserService userService) {
        this.userRepository= userRepository;
        this.userService = userService;

    }


    public List<Teacher> getFullInfoAllTeacher(){

        int theMentors = userRepository.findByRole(Role.TEACHER).size();
        if(theMentors!=0){
           return teacherRepository.findAll();
        }
        throw new TeacherNotFoundException("Teachers not found");

    }
}
