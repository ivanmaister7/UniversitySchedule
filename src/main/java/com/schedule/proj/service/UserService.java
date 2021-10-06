package com.schedule.proj.service;

import com.schedule.proj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }









}
