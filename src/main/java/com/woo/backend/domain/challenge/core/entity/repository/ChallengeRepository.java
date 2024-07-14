package com.woo.backend.domain.challenge.core.entity.repository;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
