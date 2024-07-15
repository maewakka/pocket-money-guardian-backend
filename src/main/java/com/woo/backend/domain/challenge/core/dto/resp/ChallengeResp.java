package com.woo.backend.domain.challenge.core.dto.resp;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import com.woo.backend.domain.user.dto.resp.UserResp;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChallengeResp {

    private Long id;
    private String name;
    private Integer limit;
    private Integer initialDay;
    private UserResp owner;
    private List<UserResp> participants;

    public static ChallengeResp of(Challenge challenge, List<UserResp> participants) {
        return ChallengeResp.builder()
                .id(challenge.getId())
                .name(challenge.getName())
                .limit(challenge.getLimit())
                .initialDay(challenge.getInitialDay())
                .participants(participants)
                .owner(UserResp.of(challenge.getOwner()))
                .build();
    }
}
