package com.woo.backend.global.config;

import com.woo.exception.config.ErrorConfig;
import com.woo.exception.handler.RestExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public RestExceptionHandler setRestExceptionHandler() throws Exception {
        ErrorConfig errorConfig = ErrorConfig.build();

        return RestExceptionHandler.setErrorConfig(errorConfig);
    }
}
