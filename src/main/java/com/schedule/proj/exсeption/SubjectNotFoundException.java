package com.schedule.proj.exсeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "subject not found")
public class SubjectNotFoundException extends RuntimeException {
}
