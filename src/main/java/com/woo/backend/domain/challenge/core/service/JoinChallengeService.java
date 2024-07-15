package com.woo.backend.domain.challenge.core.service;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.challenge.core.entity.repository.ParticipantsRepository;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JoinChallengeService {

    private final ParticipantsRepository participantsRepository;
    private final UserRepository userRepository;

    public void addParticipantsToChallenge(List<Long> ids, Challenge challenge) {
        List<Long> challengeUserList = participantsRepository.findAllByChallenge(challenge).stream()
                .map(participants -> participants.getParticipant().getId()).toList();

        List<Participants> participants = new ArrayList<>();

        ids.stream().filter(id -> !challengeUserList.contains(id))
                .map(id -> userRepository.findById(id).orElseThrow(() -> new BizException("user_not_found")))
                .forEach(user -> participants.add(Participants.builder()
                                .participant(user)
                                .challenge(challenge)
                                .build()));

        participantsRepository.saveAll(participants);
    }

}
