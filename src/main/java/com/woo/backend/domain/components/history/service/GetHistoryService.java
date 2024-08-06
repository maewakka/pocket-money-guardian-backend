package com.woo.backend.domain.components.history.service;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.components.history.dto.resp.GetChallengeHistoryResp;
import com.woo.backend.domain.components.history.entity.ChallengeHistory;
import com.woo.backend.domain.components.history.entity.repository.ChallengeHistoryRepository;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetHistoryService {

    private final ChallengeHistoryRepository challengeHistoryRepository;

    public List<GetChallengeHistoryResp> getHistories(Participants participants, Integer year, Integer month) {
        Challenge challenge = participants.getChallenge();

        LocalDate now = LocalDate.now();
        LocalDate startDate = LocalDate.of(year, month, challenge.getInitialDay());
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        if(now.isBefore(endDate)) endDate = now;

        List<GetChallengeHistoryResp> resp = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            List<ChallengeHistory> histories = challengeHistoryRepository.findAllByParticipantAndRegDate(participants, date);

            resp.add(GetChallengeHistoryResp.builder()
                            .date(date)
                            .usedAmount(getTotalAmount(histories))
                            .remainingAmount(challenge.getLimit() - getTotalAmount(histories))
                            .dateHistory(histories.stream().map(GetChallengeHistoryResp.History::of).collect(Collectors.toList()))
                    .build());
        }

        return resp.stream()
                .sorted(Comparator.comparing(GetChallengeHistoryResp::getDate).reversed())
                .collect(Collectors.toList());
    }

    public ChallengeHistory getHistoryById(Long historyId) {
        return challengeHistoryRepository.findById(historyId).orElseThrow(() -> new BizException("history_not_found"));
    }

    private Integer getTotalAmount(List<ChallengeHistory> histories) {
        return histories.stream().mapToInt(ChallengeHistory::getAmount).sum();
    }

}
