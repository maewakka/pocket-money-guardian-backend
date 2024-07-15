package com.woo.backend.domain.components.history.controller;

import com.woo.backend.domain.components.history.dto.req.ChallengeHistoryReq;
import com.woo.backend.domain.components.history.dto.req.GetChallengeHistoryReq;
import com.woo.backend.domain.components.history.dto.resp.GetChallengeHistoryResp;
import com.woo.backend.domain.components.history.facade.ChallengeHistoryFacade;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.global.security.dto.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class ChallengeHistoryController {

    private final ChallengeHistoryFacade challengeHistoryFacade;

    @PostMapping("/register")
    public ResponseEntity<String> registerHistory(@RequestBody ChallengeHistoryReq req, @CurrentUser User user) {
        challengeHistoryFacade.createHistory(req, user);

        return ResponseEntity.ok().body("사용 내역 등록이 완료되었습니다.");
    }

    @PostMapping("/list")
    public List<GetChallengeHistoryResp> getChallengeHistories(@RequestBody GetChallengeHistoryReq req) {
        return challengeHistoryFacade.getHistoryByParticipant(req);
    }


}
