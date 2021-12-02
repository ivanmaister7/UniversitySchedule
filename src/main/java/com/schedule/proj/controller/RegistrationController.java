package com.schedule.proj.controller;


import com.schedule.proj.exсeption.RegistrationException;
import com.schedule.proj.model.DTO.UserDTO;
import com.schedule.proj.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/registration")
public class RegistrationController {


    @Autowired
    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String createUserForm(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "user-create";
    }

    //method for registration
    @Operation(summary = "method for registration")
    @PostMapping
    ResponseEntity<?> registration(UserDTO userDTO){

        Map<String, String> res = new HashMap<>();

        try{
            String result = registrationService.registration(userDTO);
            res.put("message", result);
            return ResponseEntity.status(201).body(res);
        }
        catch(RegistrationException e){
            res.put("message",e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }

    }
}