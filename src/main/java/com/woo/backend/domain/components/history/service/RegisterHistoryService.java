package com.woo.backend.domain.components.history.service;

import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.components.history.dto.req.ChallengeHistoryReq;
import com.woo.backend.domain.components.history.entity.repository.ChallengeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterHistoryService {

    private final ChallengeHistoryRepository challengeHistoryRepository;

    public void createHistory(Participants participant, ChallengeHistoryReq req) {
        challengeHistoryRepository.save(req.toEntity(participant));
    }

}
