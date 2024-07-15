package com.woo.backend.domain.challenge.core.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class JoinChallengeReq {

    private List<Long> userIds;
    private Long challengeId;

}
