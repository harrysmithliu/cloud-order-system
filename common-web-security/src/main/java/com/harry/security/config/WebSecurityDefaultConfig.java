package com.harry.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityDefaultConfig {
    @Bean
    public SecurityFilterChain defaultSecurityChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // let go all request
                .sessionManagement(sess -> sess.disable())
                .securityContext(ctx -> ctx.disable())
                .httpBasic(httpBasic -> httpBasic.disable()); // forbid Basic Auth

        return http.build();
    }
}
