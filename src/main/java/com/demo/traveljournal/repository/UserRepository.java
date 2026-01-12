package com.demo.traveljournal.repository;

import com.demo.traveljournal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    Optional<User> findById(Long id);
    List<User> findAll();

}
