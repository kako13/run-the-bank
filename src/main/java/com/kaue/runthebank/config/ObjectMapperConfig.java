package com.kaue.runthebank.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
