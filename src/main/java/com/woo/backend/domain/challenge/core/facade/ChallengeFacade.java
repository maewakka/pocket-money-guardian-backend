package com.woo.backend.domain.challenge.core.facade;

import com.woo.backend.domain.challenge.core.dto.req.ChallengeReq;
import com.woo.backend.domain.challenge.core.dto.req.JoinChallengeReq;
import com.woo.backend.domain.challenge.core.dto.resp.ChallengeResp;
import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.challenge.core.service.*;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.service.GetUserService;
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
    private final GetParticipantService getParticipantService;
    private final GetUserService getUserService;
    private final DeleteParticipantsService deleteParticipantsService;

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

    @Transactional
    public void dropParticipants(User user, Long dropUserId, Long challengeId) {
        Challenge challenge = getChallengeService.getChallengeById(challengeId);
        User dropUser = getUserService.findUserById(dropUserId);
        Participants participants = getParticipantService.getParticipantByChallengeAndUser(challenge, dropUser);

        deleteParticipantsService.dropParticipant(challenge, user, participants);
    }
}
