package com.woo.backend.domain.challenge.core.dto.resp;

import com.woo.backend.domain.challenge.core.entity.Challenge;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOwnChallengeListResp {

    private Long id;
    private String name;

    public static GetOwnChallengeListResp of(Challenge challenge) {
        return GetOwnChallengeListResp.builder()
                .id(challenge.getId())
                .name(challenge.getName())
                .build();
    }

}
