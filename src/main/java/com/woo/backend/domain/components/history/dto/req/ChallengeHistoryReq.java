package com.woo.backend.domain.components.history.dto.req;

import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.components.history.entity.ChallengeHistory;
import com.woo.backend.domain.user.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChallengeHistoryReq {
    private LocalDate date;
    private String content;
    private Integer amount;
    private Long challengeId;

    public ChallengeHistory toEntity(Participants participant) {
        return ChallengeHistory.builder()
                .amount(amount)
                .content(content)
                .regDate(date)
                .participant(participant)
                .build();
    }

}
