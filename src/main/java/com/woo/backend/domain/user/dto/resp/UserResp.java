package com.woo.backend.domain.user.dto.resp;

import com.woo.backend.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResp {
    private Long id;
    private String nickName;

    public static UserResp of(User user) {
        return UserResp.builder()
                .id(user.getId())
                .nickName(user.getNickName())
                .build();
    }
}
