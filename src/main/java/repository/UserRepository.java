package repository;

import com.schedule.proj.model.Role;
import com.schedule.proj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {





    @Query("Select user from User user")
    List<User> findAll();


    Collection<Object> findByRole(Role teacher);
}
