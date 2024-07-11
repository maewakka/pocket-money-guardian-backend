package com.woo.backend.global.exception.handler;

import com.woo.backend.global.exception.config.ErrorConfig;
import com.woo.backend.global.exception.config.ErrorConfig.ErrorInfo;
import com.woo.backend.global.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class BizExceptionHandler {

    private final ErrorConfig errorConfig;

    @ExceptionHandler(BizException.class)
    public ResponseEntity<Object> handleCustomException(BizException e) {
        ErrorInfo info = errorConfig.getErrors().get(e.getKey());

        log.error("BizException : " + info.getMessage());
        e.printStackTrace();

        return handleExceptionInternal(info);
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorInfo info) {
        return ResponseEntity.status(info.getStatus())
                .body(info);
    }

}
