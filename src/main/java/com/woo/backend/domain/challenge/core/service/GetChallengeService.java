package com.woo.backend.domain.challenge.core.service;

import com.woo.backend.domain.challenge.core.dto.resp.ChallengeResp;
import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.challenge.core.entity.repository.ChallengeRepository;
import com.woo.backend.domain.challenge.core.entity.repository.ParticipantsRepository;
import com.woo.backend.domain.user.dto.resp.UserResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ParticipantsRepository participantsRepository;

    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id).orElseThrow(() -> new BizException("challenge_not_found"));
    }

    public ChallengeResp getChallengeDetail(Long id, User user) {
        Challenge challenge = challengeRepository.findById(id).orElseThrow(() -> new BizException("challenge_not_found"));
        List<Long> joinParticipants = participantsRepository.findAllByChallenge(challenge).stream().map(participants -> participants.getParticipant().getId()).toList();

        if(!joinParticipants.contains(user.getId())) throw new BizException("not_join_challenge");
        List<UserResp> participants = participantsRepository.findAllByChallenge(challenge).stream().map(participant -> UserResp.of(participant.getParticipant())).toList();

        return ChallengeResp.of(challenge, participants);
    }
}
