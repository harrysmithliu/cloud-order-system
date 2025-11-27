package com.harry.security.pwd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security configuration for password encryption and authentication
 * Provides PasswordEncoder bean for all modules
 */
@Configuration
public class PasswordConfig {

    /**
     * Configure BCrypt password encoder with strength 12
     * Strength 12 provides good balance between security and performance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
