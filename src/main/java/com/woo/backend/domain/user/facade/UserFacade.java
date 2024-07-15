package com.woo.backend.domain.user.facade;

import com.woo.backend.domain.user.dto.req.SignInReq;
import com.woo.backend.domain.user.dto.req.SignUpReq;
import com.woo.backend.domain.user.dto.resp.SignInResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.service.EmailVerificationService;
import com.woo.backend.domain.user.service.GetUserService;
import com.woo.backend.domain.user.service.RegisterUserService;
import com.woo.backend.global.security.util.JwtTokenProvider;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final RegisterUserService registerUserService;
    private final GetUserService getUserService;
    private final EmailVerificationService emailVerificationService;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUp(SignUpReq req) {
        registerUserService.createUser(req);
    }

    @Transactional
    public SignInResp signIn(SignInReq req) {
        User user = getUserService.findUserByEmailAndPassword(req);

        return SignInResp.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getEmail()))
                .build();
    }

    public void sendVerificationEmail(String email) {
        emailVerificationService.sendVerificationEmail(email);
    }

    public void verifyingCode(String email, String code) {
        emailVerificationService.verifyingCode(email, code);
    }
}
