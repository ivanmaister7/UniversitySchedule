package com.schedule.proj.service;

import com.schedule.proj.exeption.TeacherNotFoundException;
import com.schedule.proj.model.Role;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.TeacherRepository;
import repository.UserRepository;


import java.util.Optional;

@Transactional
@Service
public class TeacherService {

    TeacherRepository teacherRepository;
    UserRepository userRepository;
    UserService userService;

    public TeacherService(UserService userService) {
        this.userRepository= userRepository;
        this.userService = userService;

    }


    public Optional<User> getFullInfoAllTeacher(){

        int theMentors = userRepository.findByRole(Role.TEACHER).size();
        if(theMentors!=0){
           return teacherRepository.findAll();
        }
        throw new TeacherNotFoundException("Teachers not found");

    }
}
