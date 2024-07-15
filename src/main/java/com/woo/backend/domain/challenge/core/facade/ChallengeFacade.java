package com.woo.backend.domain.challenge.core.facade;

import com.woo.backend.domain.challenge.core.dto.req.ChallengeReq;
import com.woo.backend.domain.challenge.core.dto.req.JoinChallengeReq;
import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.service.GetChallengeService;
import com.woo.backend.domain.challenge.core.service.JoinChallengeService;
import com.woo.backend.domain.challenge.core.service.RegisterChallengeService;
import com.woo.backend.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChallengeFacade {

    private final RegisterChallengeService registerChallengeService;
    private final GetChallengeService getChallengeService;
    private final JoinChallengeService joinChallengeService;

    @Transactional
    public void registerChallenge(User user, ChallengeReq req) {
        registerChallengeService.createChallenge(user, req);
    }

    @Transactional
    public void addParticipantsToChallenge(JoinChallengeReq req) {
        Challenge challenge = getChallengeService.getChallengeById(req.getChallengeId());

        joinChallengeService.addParticipantsToChallenge(req.getUserIds(), challenge);
    }
}
