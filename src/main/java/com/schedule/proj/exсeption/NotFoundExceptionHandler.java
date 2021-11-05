package com.schedule.proj.exсeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler({ StudentNotFoundException.class })
    public ResponseEntity<Object> handleStudentNotFound(
            Exception ex, WebRequest request) {
        String[] pathSplited = ((ServletWebRequest)request).getRequest().getRequestURI().split("/");
        return ResponseEntity.badRequest().body(
                "Student not found, id: " + pathSplited[3]);
    }

    @ExceptionHandler({ TeacherNotFoundException.class })
    public ResponseEntity<Object> handleTeacherNotFound(
            Exception ex, WebRequest request) {
        String[] pathSplited = ((ServletWebRequest)request).getRequest().getRequestURI().split("/");
        return ResponseEntity.badRequest().body(
                "Teacher not found, id: " + pathSplited[3]);
    }

    @ExceptionHandler({ SubjectNotFoundException.class })
    public ResponseEntity<Object> handleSubjectNotFound(
            Exception ex, WebRequest request) {
        String[] pathSplited = ((ServletWebRequest)request).getRequest().getRequestURI().split("/");
        return ResponseEntity.badRequest().body(
                "Subject not found: " + pathSplited[3]);
    }
}
