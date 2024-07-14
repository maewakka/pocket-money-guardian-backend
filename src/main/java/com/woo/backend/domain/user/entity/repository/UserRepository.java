package com.woo.backend.domain.user.entity.repository;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);

}
