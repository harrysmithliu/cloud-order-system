package com.harry.starter.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class WebLogAspect {
    private final ObjectMapper objectMapper;

    @Around("execution(* com.harry..controller..*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = attributes.getRequest();
        long start = System.currentTimeMillis();

        log.info("[REQUEST] {} {} params={}",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString());

        try {
            log.info("[REQUEST-BODY] {}", objectMapper.writeValueAsString(joinPoint.getArgs()));
        } catch (Exception ignored) {}

        Object result = joinPoint.proceed();

        long cost = System.currentTimeMillis() - start;
        log.info("[RESPONSE] {} cost={}ms", request.getRequestURI(), cost);

        try {
            log.info("[RESPONSE-BODY] {}", objectMapper.writeValueAsString(result));
        } catch (Exception ignored) {}

        return result;
    }

}
