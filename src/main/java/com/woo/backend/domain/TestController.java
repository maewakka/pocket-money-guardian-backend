package com.woo.backend.domain;

import com.woo.backend.global.exception.util.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public void test() {
        log.info("TESTSETS");
        throw new BizException("not_found");
    }

}
