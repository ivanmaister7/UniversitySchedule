package com.schedule.proj.repository;


import org.apache.tomcat.jni.User;

import java.util.Optional;

public interface TeacherRepository {

      Optional<User> findById(int id);
      Optional<User> findAll();

      Optional<User> findByEmail(String email);



 }
