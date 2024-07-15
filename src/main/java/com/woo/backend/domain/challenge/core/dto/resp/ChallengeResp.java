package com.woo.backend.domain.challenge.core.dto.resp;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeResp {

    private Long id;
    private String name;
    private Integer limit;
    private Integer initialDay;

    public static ChallengeResp of(Challenge challenge) {
        return ChallengeResp.builder()
                .id(challenge.getId())
                .name(challenge.getName())
                .limit(challenge.getLimit())
                .initialDay(challenge.getInitialDay())
                .build();
    }
}
