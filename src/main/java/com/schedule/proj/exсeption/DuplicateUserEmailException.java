package com.schedule.proj.exсeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "duplicate user email")
public class DuplicateUserEmailException extends RuntimeException{
}
