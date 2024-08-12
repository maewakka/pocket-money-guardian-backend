package com.woo.backend.domain.components.history.dto.req;

import com.woo.backend.domain.challenge.core.entity.Participants;
import com.woo.backend.domain.components.history.entity.ChallengeHistory;
import com.woo.backend.domain.user.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChallengeHistoryReq {

    @NotNull
    private LocalDate date;
    @NotNull
    private String content;
    @Min(0)
    private Integer amount;
    @NotNull
    private Long challengeId;

    public ChallengeHistory toEntity(Participants participant) {
        return ChallengeHistory.builder()
                .amount(amount == null ? 0 : amount)
                .content(content)
                .regDate(date)
                .participant(participant)
                .build();
    }

}
