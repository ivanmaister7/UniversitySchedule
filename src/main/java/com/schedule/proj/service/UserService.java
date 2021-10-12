package com.schedule.proj.service;

import com.schedule.proj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        int userId = Integer.parseInt(id);
        return userRepository.findOneById(userId);
    }





}
