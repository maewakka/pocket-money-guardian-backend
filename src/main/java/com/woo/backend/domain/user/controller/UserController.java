package com.woo.backend.domain.user.controller;

import com.woo.backend.domain.user.dto.req.SignInReq;
import com.woo.backend.domain.user.dto.req.SignUpReq;
import com.woo.backend.domain.user.dto.resp.SignInResp;
import com.woo.backend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("users")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpReq req) {
        userFacade.signUp(req);

        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("sign-in")
    public SignInResp signIn(@RequestBody SignInReq req) {
        return userFacade.signIn(req);
    }

}
