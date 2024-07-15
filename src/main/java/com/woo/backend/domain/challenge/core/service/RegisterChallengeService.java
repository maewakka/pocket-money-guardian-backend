package com.woo.backend.domain.challenge.core.service;

import com.woo.backend.domain.challenge.core.dto.req.ChallengeReq;
import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.challenge.core.entity.repository.ChallengeRepository;
import com.woo.backend.domain.challenge.core.entity.repository.ParticipantsRepository;
import com.woo.backend.domain.user.entity.User;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ParticipantsRepository participantsRepository;

    public void createChallenge(User user, ChallengeReq req) {
        Challenge challenge = challengeRepository.save(req.toEntity(user));
        participantsRepository.save(Participants.builder()
                        .challenge(challenge)
                        .participant(user)
                        .build());
    }

}
