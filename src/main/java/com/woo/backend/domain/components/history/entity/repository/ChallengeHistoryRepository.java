package com.woo.backend.domain.components.history.entity.repository;

import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.components.history.entity.ChallengeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ChallengeHistoryRepository extends JpaRepository<ChallengeHistory, Long> {


    @Query(" SELECT ch FROM ChallengeHistory ch WHERE ch.participant = :participant AND ch.regDate BETWEEN :startDate AND :endDate ")
    List<ChallengeHistory> findAllHistoryByParticipantsInTimeRange(Participants participant, LocalDate startDate, LocalDate endDate);

    List<ChallengeHistory> findAllByParticipantAndRegDate(Participants participants, LocalDate regDate);
}
