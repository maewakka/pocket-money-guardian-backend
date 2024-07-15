package com.woo.backend.domain.challenge.core.service;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.repository.ChallengeRepository;
import com.woo.backend.domain.user.entity.User;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteChallengeService {

    private final ChallengeRepository challengeRepository;

    public void deleteChallenge(Challenge challenge, User currentUser) {
        if(!challenge.getOwner().getId().equals(currentUser.getId())) throw new BizException("not_challenge_owner");


    }

}
