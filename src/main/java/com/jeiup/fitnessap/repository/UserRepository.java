package com.jeiup.fitnessap.repository;

import com.jeiup.fitnessap.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String name);
}
