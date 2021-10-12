package com.schedule.proj.repository;


import com.schedule.proj.model.Teacher;
import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
      Teacher findById(int id);
      List<Teacher> findAll();
      Teacher findByEmail(String email);
 }
