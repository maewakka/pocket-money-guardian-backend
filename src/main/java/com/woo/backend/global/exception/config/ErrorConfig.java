package com.woo.backend.global.exception.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
@ConfigurationProperties(prefix = "exception")
public class ErrorConfig {

    private Map<String, ErrorInfo> errors = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        Resource resource = new ClassPathResource("error/exception.yml");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        errors = mapper.readValue(resource.getInputStream(), mapper.getTypeFactory().constructMapType(HashMap.class, String.class, ErrorInfo.class));
    }

    @Data
    public static class ErrorInfo {
        private int status;
        private String message;
    }
}
