package com.woo.backend.domain.components.history.service;

import com.woo.backend.domain.components.history.entity.ChallengeHistory;
import com.woo.backend.domain.components.history.entity.repository.ChallengeHistoryRepository;
import com.woo.backend.domain.user.entity.User;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteHistoryService {

    private final ChallengeHistoryRepository challengeHistoryRepository;

    public void deleteChallengeHistory(ChallengeHistory history, User user) {
        if(!history.getParticipant().getParticipant().getId().equals(user.getId())) throw new BizException("history_not_own");

        challengeHistoryRepository.delete(history);
    }

}
