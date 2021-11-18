package com.schedule.proj.repository;

import com.schedule.proj.model.Teacher;
import com.schedule.proj.model.User;
import com.schedule.proj.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM USER u where u.user_role = ?1")
//    Collection<User> findByUserRole(UserRole teacher);
//}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select t from User t where t.email = ?1")


    Optional<User> findById(int id);

    @Query("Select user from User user")
    List<User> findAll();

    Optional<User> findByEmail(String email);

    User findOneById(int id);

    User findUserByEmail(String email);



}
