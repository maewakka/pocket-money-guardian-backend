package com.woo.backend.domain.components.history.dto.resp;

import com.woo.backend.domain.components.history.entity.ChallengeHistory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class GetChallengeHistoryResp {

    private LocalDate date;
    private Integer usedAmount;
    private Integer remainingAmount;
    private List<History> dateHistory;

    @Data
    @Builder
    public static class History {
        private Long historyId;
        private String content;
        private Integer amount;

        public static History of(ChallengeHistory challengeHistory) {
            return History.builder()
                    .historyId(challengeHistory.getId())
                    .content(challengeHistory.getContent())
                    .amount(challengeHistory.getAmount())
                    .build();
        }
    }

}
