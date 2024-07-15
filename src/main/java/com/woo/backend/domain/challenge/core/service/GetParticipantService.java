package com.woo.backend.domain.challenge.core.service;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.challenge.core.entity.repository.ParticipantsRepository;
import com.woo.backend.domain.user.entity.User;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetParticipantService {

    private final ParticipantsRepository participantsRepository;

    public Participants getParticipantByChallengeAndUser(Challenge challenge, User user) {
        return participantsRepository.findParticipantsByChallengeAndParticipant(challenge, user).orElseThrow(() -> new BizException("participant_not_found"));
    }
}
