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
public class DeleteParticipantsService {

    private final ParticipantsRepository participantsRepository;

    public void dropParticipant(Challenge challenge, User currentUser, Participants participant) {
        if(!challenge.getOwner().getId().equals(currentUser.getId())) throw new BizException("not_challenge_owner");
        if(challenge.getOwner().getId().equals(participant.getParticipant().getId())) throw new BizException("can_not_drop_own");

        participantsRepository.delete(participant);
    }

}
