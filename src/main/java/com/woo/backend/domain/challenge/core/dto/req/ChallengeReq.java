package com.woo.backend.domain.challenge.core.dto.req;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import lombok.Data;

@Data
public class ChallengeReq {

    private Long id;
    private String name;
    private Integer limit;
    private Integer initialDay;

    public Challenge toEntity() {
        return Challenge.builder()
                .name(name)
                .limit(limit)
                .initialDay(initialDay)
                .build();
    }

}
