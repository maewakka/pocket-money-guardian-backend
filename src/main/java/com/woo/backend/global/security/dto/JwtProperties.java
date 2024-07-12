package com.woo.backend.global.security.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private Long accessTime;
    private String prefix;
    private String header;
    private String secretKey;
}