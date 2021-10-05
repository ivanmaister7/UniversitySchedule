package com.schedule.proj.service;


import com.schedule.proj.model.AuthorizationUser;
import com.schedule.proj.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorizationService {
    @Autowired
    private AuthorizationUser authorizationUser;

    @Autowired
    public void authorize(User user) {
        //authorize process
    }
}
