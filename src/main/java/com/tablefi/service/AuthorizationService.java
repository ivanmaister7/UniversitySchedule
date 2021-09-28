package com.tablefi.service;

import com.tablefi.model.AuthorizationUser;
import com.tablefi.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorizationService {
    @Autowired
    private AuthorizationUser authorizationUser;

    @Autowired
    public void authorize(User user) {
        //authorize process
    }
}
