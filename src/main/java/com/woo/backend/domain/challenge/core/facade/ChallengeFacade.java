package com.woo.backend.domain.challenge.core.facade;

import com.woo.backend.domain.challenge.core.dto.req.ChallengeReq;
import com.woo.backend.domain.challenge.core.dto.req.JoinChallengeReq;
import com.woo.backend.domain.challenge.core.dto.resp.ChallengeResp;
import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.service.GetChallengeService;
import com.woo.backend.domain.challenge.core.service.JoinChallengeService;
import com.woo.backend.domain.challenge.core.service.RegisterChallengeService;
import com.woo.backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public ChallengeResp getChallengeDetails(Long id, User user) {
        return getChallengeService.getChallengeDetail(id, user);
    }
}
