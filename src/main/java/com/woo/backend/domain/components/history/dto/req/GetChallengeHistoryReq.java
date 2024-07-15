package com.woo.backend.domain.components.history.dto.req;

import lombok.Data;

@Data
public class GetChallengeHistoryReq {

    private Long userId;
    private Long challengeId;
    private Integer year;
    private Integer month;

}
