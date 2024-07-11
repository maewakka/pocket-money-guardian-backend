package com.woo.backend.global.exception.util;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final String key;

    public BizException(String key) {
        super(key);
        this.key = key;
    }
}
