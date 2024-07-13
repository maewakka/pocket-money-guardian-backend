package com.woo.backend.domain.user.facade;

import com.woo.backend.domain.user.dto.req.SignInReq;
import com.woo.backend.domain.user.dto.req.SignUpReq;
import com.woo.backend.domain.user.dto.resp.SignInResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.service.GetUserService;
import com.woo.backend.domain.user.service.RegisterUserService;
import com.woo.backend.global.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final RegisterUserService registerUserService;
    private final GetUserService getUserService;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(SignUpReq req) {
        registerUserService.createUser(req);
    }

    public SignInResp signIn(SignInReq req) {
        User user = getUserService.findUserByEmailAndPassword(req);

        return SignInResp.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getEmail()))
                .build();
    }
}
