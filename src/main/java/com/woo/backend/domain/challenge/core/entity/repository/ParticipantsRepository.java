package com.woo.backend.domain.challenge.core.entity.repository;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.user.entity.User;
import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {

    Boolean existsByParticipant(User participant);

    List<Participants> findAllByChallenge(Challenge challenge);

    Optional<Participants> findParticipantsByChallengeAndParticipant(Challenge challenge, User participant);

}
