package com.schedule.proj.repository;

import com.schedule.proj.model.User;
import com.schedule.proj.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM USER u where u.user_role = ?1")
//    Collection<User> findByUserRole(UserRole teacher);
//}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}