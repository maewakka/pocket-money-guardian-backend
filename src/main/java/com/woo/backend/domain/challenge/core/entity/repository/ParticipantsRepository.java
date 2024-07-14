package com.woo.backend.domain.challenge.core.entity.repository;

import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {

    Boolean existsByParticipant(User participant);

}
