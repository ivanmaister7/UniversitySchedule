package com.schedule.proj.controller;


import com.schedule.proj.model.DTO.StudentGeneralResponseDTO;
import com.schedule.proj.model.Student;
import com.schedule.proj.model.User;
import com.schedule.proj.service.StudentService;
import com.schedule.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public String getUserPage(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.getUserById(id.intValue()));
        return "user-page";
    }

    @GetMapping("/{id}/profile")
    public String getUserPageProfile(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.getUserById(id.intValue()));
        return "user-page-profile";
    }

    @GetMapping("/{id}/profile/edit")
    public String getUserPageProfileEdit(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.getUserById(id.intValue()));
        model.addAttribute("years", new int[]{1, 2, 3, 4, 5, 6});
        return "user-page-profile-edit";
    }

    @PostMapping("/{id}/profile/edit")
    public String updateUserPageProfileEdit(@ModelAttribute("user") User user,
                                            @PathVariable("id")Long id, Model model,
                                            HttpServletRequest request,
                                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("user", userService.getUserById(id.intValue()));
            return "user-page-profile-edit";
        }
        Student st = user.getStudent();
        studentService.updateStudentByToken(new StudentGeneralResponseDTO(null,
                null,
                st.getFaculty(),
                st.getSpeciality(),
                st.getStudentYear()), request );
        return "redirect:/api/user/"+id.toString()+"/profile";
    }












}

