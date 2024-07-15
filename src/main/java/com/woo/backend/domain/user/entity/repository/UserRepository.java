package com.woo.backend.domain.user.entity.repository;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByNickName(String nickName);
    @Query("SELECT u FROM User u WHERE u.nickName LIKE '%' || :nickName ||'%' ")
    List<User> findAllUserByNickName(@Param("nickName") String nickName);

}
