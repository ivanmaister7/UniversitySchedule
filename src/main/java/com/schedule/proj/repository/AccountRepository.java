package com.schedule.proj.repository;

import com.schedule.proj.model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    Optional<Accounts> findById(int id);

}

