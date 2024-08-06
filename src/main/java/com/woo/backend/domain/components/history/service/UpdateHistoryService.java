package com.woo.backend.domain.components.history.service;

import com.woo.backend.domain.components.history.dto.resp.GetChallengeHistoryResp;
import com.woo.backend.domain.components.history.entity.ChallengeHistory;
import com.woo.backend.domain.components.history.entity.repository.ChallengeHistoryRepository;
import com.woo.backend.domain.user.entity.User;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateHistoryService {

    private final ChallengeHistoryRepository challengeHistoryRepository;

    public void updateHistory(ChallengeHistory history, User user, String content, Integer amount) {
        if(!history.getParticipant().getParticipant().getId().equals(user.getId())) throw new BizException("history_not_own");

        history.updateHistory(content, amount);
        challengeHistoryRepository.save(history);
    }

}
