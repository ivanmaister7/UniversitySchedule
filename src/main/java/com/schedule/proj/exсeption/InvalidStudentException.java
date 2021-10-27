package com.schedule.proj.exсeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "student is invalid")
public class InvalidStudentException extends RuntimeException{
}
