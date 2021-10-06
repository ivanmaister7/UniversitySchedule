package com.schedule.proj.exeption;

public class TeacherNotFoundException extends RuntimeException{

    public TeacherNotFoundException(String message){
        super(message);
    }

    public static class UnkownIdentifierException {
    }
}
