package com.schedule.proj.repository;

import com.schedule.proj.model.Role;
import com.schedule.proj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Collection<Object> findByUserRole(Role teacher);

    User findOneById(int id);
}