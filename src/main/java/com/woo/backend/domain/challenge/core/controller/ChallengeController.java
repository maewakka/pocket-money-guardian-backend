package com.woo.backend.domain.challenge.core.controller;

import com.woo.backend.domain.challenge.core.dto.req.ChallengeReq;
import com.woo.backend.domain.challenge.core.dto.req.JoinChallengeReq;
import com.woo.backend.domain.challenge.core.dto.resp.ChallengeResp;
import com.woo.backend.domain.challenge.core.facade.ChallengeFacade;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.global.security.dto.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeFacade challengeFacade;

    @PostMapping("/create")
    public ResponseEntity<String> createChallenge(@RequestBody ChallengeReq req, @CurrentUser User user) {
        challengeFacade.registerChallenge(user, req);

        return ResponseEntity.ok("챌린지 생성에 성공하였습니다.");
    }

    @PostMapping("/join")
    public ResponseEntity<String> bringInChallenge(@RequestBody JoinChallengeReq req) {
        challengeFacade.addParticipantsToChallenge(req);

        return ResponseEntity.ok("유저를 추가하였습니다.");
    }

    @GetMapping("/{id}")
    public ChallengeResp getChallenge(@PathVariable(name = "id") Long id, @CurrentUser User user) {
        return challengeFacade.getChallengeDetails(id, user);
    }

}
