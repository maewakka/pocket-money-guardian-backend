package com.woo.backend.domain.components.history.dto.req;

import lombok.Data;

@Data
public class UpdateChallengeHistoryReq {
    private Long historyId;
    private String content;
    private Integer amount;
}
