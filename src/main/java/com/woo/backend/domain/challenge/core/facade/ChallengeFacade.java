package com.woo.backend.domain.challenge.core.facade;

import com.woo.backend.domain.challenge.core.dto.req.ChallengeReq;
import com.woo.backend.domain.challenge.core.service.RegisterChallengeService;
import com.woo.backend.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChallengeFacade {

    private final RegisterChallengeService registerChallengeService;

    @Transactional
    public void registerChallenge(User user, ChallengeReq req) {
        registerChallengeService.createChallenge(user, req);
    }

}
