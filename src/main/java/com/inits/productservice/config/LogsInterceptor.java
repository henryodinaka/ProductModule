package com.inits.productservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


@Configuration
@EnableWebMvc
@Slf4j
@RequiredArgsConstructor
public class LogsInterceptor implements WebMvcConfigurer, HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Interceptor preHandle process execution to URL {}  from IP Address {} was initiated At {}", request.getRequestURI(), request.getRemoteAddr(), LocalDateTime.now());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("Interceptor After completion process execution to URL {}  from IP Address {} was completed At {}", request.getRequestURI(), request.getRemoteAddr(), LocalDateTime.now());
    }

}
