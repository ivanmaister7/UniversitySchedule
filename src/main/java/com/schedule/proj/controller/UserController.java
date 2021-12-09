package com.schedule.proj.controller;


import com.schedule.proj.exсeption.RegistrationException;
import com.schedule.proj.model.DTO.PasswordDTO;
import com.schedule.proj.model.User;
import com.schedule.proj.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    //select all accounts
//    @Operation(summary = "select all users")
    @GetMapping("/student/{id}")
    public String getUserPage(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.getUserById(id.intValue()));
        //model.addAttribute("user", new User());
        return "user-page";
    }


    @Operation(summary = "change password")
    @PutMapping("/changePassword")
    ResponseEntity<?> changePassword(@RequestBody PasswordDTO dto,
                                     HttpServletRequest request) {

        Map<String, String> res = new HashMap<>();
        try {
            String result = userService.changePassword(request, dto.getOldPassword(), dto.getNewPassword());
            res.put("message", result);
            return ResponseEntity.ok(res);
        } catch (RegistrationException | UsernameNotFoundException e) {
            res.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }










}

