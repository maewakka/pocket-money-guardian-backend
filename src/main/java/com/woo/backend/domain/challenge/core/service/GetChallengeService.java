package com.woo.backend.domain.challenge.core.service;

import com.woo.backend.domain.challenge.core.dto.resp.ChallengeResp;
import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.repository.ChallengeRepository;
import com.woo.backend.domain.challenge.core.entity.repository.ParticipantsRepository;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ParticipantsRepository participantsRepository;

    public ChallengeResp getChallengeById(Long id) {
        Challenge challenge = challengeRepository.findById(id).orElseThrow(() -> new BizException("challenge_not_found"));
        return ChallengeResp.of(challenge);
    }
}
