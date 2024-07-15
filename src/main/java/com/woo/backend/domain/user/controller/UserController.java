package com.woo.backend.domain.user.controller;

import com.woo.backend.domain.user.dto.req.SignInReq;
import com.woo.backend.domain.user.dto.req.SignUpReq;
import com.woo.backend.domain.user.dto.resp.SignInResp;
import com.woo.backend.domain.user.dto.resp.UserResp;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.facade.UserFacade;
import com.woo.backend.global.security.dto.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpReq req) {
        userFacade.signUp(req);

        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/sign-in")
    public SignInResp signIn(@RequestBody SignInReq req) {
        return userFacade.signIn(req);
    }

    @GetMapping("/code/send")
    public ResponseEntity<String> sendVerifyingCode(@RequestParam(name = "email") String email) {
        userFacade.sendVerificationEmail(email);

        return ResponseEntity.ok("인증코드가 발송되었습니다.");
    }

    @GetMapping("/code/verify")
    public ResponseEntity<String> verifyingCode(@RequestParam(name = "email") String email, @RequestParam(name = "code") String code) {
        userFacade.verifyingCode(email, code);

        return ResponseEntity.ok("인증이 완료되었습니다.");
    }

    @GetMapping
    public List<UserResp> getAllUserByNickName(@RequestParam(name = "nickName") @Nullable String nickName, @CurrentUser User user) {
        return userFacade.getAllUserByNickName(nickName, user);
    }

}
