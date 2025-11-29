package com.harry.starter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harry.starter.logging.WebLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(WebLogAspect.class)   // load when it exists
public class WebLoggingAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public WebLogAspect webLogAspect(ObjectMapper objectMapper) {
        return new WebLogAspect(objectMapper);
    }
}
