package com.schedule.proj.controller;


import com.schedule.proj.model.User;
import com.schedule.proj.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;



    //select all accounts
    @Operation(summary = "select all users")
    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }













}

