package com.woo.backend.domain.components.history.facade;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.challenge.core.service.GetChallengeService;
import com.woo.backend.domain.challenge.core.service.GetParticipantService;
import com.woo.backend.domain.components.history.dto.req.ChallengeHistoryReq;
import com.woo.backend.domain.components.history.dto.req.GetChallengeHistoryReq;
import com.woo.backend.domain.components.history.dto.req.UpdateChallengeHistoryReq;
import com.woo.backend.domain.components.history.dto.resp.GetChallengeHistoryResp;
import com.woo.backend.domain.components.history.entity.ChallengeHistory;
import com.woo.backend.domain.components.history.service.DeleteHistoryService;
import com.woo.backend.domain.components.history.service.GetHistoryService;
import com.woo.backend.domain.components.history.service.RegisterHistoryService;
import com.woo.backend.domain.components.history.service.UpdateHistoryService;
import com.woo.backend.domain.user.entity.User;


import com.woo.backend.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChallengeHistoryFacade {

    private final RegisterHistoryService registerHistoryService;
    private final GetChallengeService getChallengeService;
    private final GetParticipantService getParticipantService;
    private final GetUserService getUserService;
    private final GetHistoryService getHistoryService;
    private final DeleteHistoryService deleteHistoryService;
    private final UpdateHistoryService updateHistoryService;

    @Transactional
    public void createHistory(ChallengeHistoryReq req, User user) {
        Challenge challenge = getChallengeService.getChallengeById(req.getChallengeId());
        Participants participant = getParticipantService.getParticipantByChallengeAndUser(challenge, user);

        registerHistoryService.createHistory(participant, req);
    }

    @Transactional(readOnly = true)
    public List<GetChallengeHistoryResp> getHistoryByParticipant(GetChallengeHistoryReq req) {
        Challenge challenge = getChallengeService.getChallengeById(req.getChallengeId());
        User user = getUserService.findUserById(req.getUserId());

        Participants participant = getParticipantService.getParticipantByChallengeAndUser(challenge, user);
        return getHistoryService.getHistories(participant, req.getYear(), req.getMonth());
    }

    @Transactional
    public void deleteHistory(Long historyId, User user) {
        ChallengeHistory history = getHistoryService.getHistoryById(historyId);

        deleteHistoryService.deleteChallengeHistory(history, user);
    }

    @Transactional
    public void updateHistory(UpdateChallengeHistoryReq req, User user) {
        ChallengeHistory history = getHistoryService.getHistoryById(req.getHistoryId());

        updateHistoryService.updateHistory(history, user, req.getContent(), req.getAmount());
    }
}
