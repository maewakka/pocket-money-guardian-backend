package com.woo.backend.domain.user.dto.req;

import lombok.Data;

@Data
public class SignInReq {
    private String email;
    private String password;
}
