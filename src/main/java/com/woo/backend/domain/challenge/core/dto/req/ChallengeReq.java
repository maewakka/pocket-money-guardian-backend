package com.woo.backend.domain.challenge.core.dto.req;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.user.entity.User;
import lombok.Data;

@Data
public class ChallengeReq {

    private Long id;
    private String name;
    private Integer limit;
    private Integer initialDay;

    public Challenge toEntity(User user) {
        return Challenge.builder()
                .name(name)
                .limit(limit)
                .initialDay(initialDay)
                .owner(user)
                .build();
    }

}
