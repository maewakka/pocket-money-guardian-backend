package com.woo.backend.domain.challenge.core.controller;

import com.woo.backend.domain.challenge.core.dto.req.ChallengeReq;
import com.woo.backend.domain.challenge.core.dto.req.JoinChallengeReq;
import com.woo.backend.domain.challenge.core.dto.resp.ChallengeResp;
import com.woo.backend.domain.challenge.core.dto.resp.GetOwnChallengeListResp;
import com.woo.backend.domain.challenge.core.facade.ChallengeFacade;
import com.woo.backend.domain.user.entity.User;
import com.woo.backend.global.security.dto.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{challengeId}/{dropUserId}")
    public ResponseEntity<String> dropUserFromChallenge(@PathVariable(name = "challengeId") Long challengeId, @PathVariable(name = "dropUserId") Long dropUserId, @CurrentUser User user) {
        challengeFacade.dropParticipants(user, dropUserId, challengeId);

        return ResponseEntity.ok("유저를 강퇴하였습니다.");
    }

    @GetMapping("/own")
    public List<GetOwnChallengeListResp> getOwnChallengeList(@CurrentUser User user) {
        return challengeFacade.getOwnChallengeList(user);
    }

}
