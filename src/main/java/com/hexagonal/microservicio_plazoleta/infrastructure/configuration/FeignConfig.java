package com.hexagonal.microservicio_plazoleta.infrastructure.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;


@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            HttpServletRequest request = getCurrentHttpRequest();
            if (request != null) {
                String authHeader = request.getHeader(AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith(BEARER)) {
                    String token = authHeader.substring(SEVEN);
                    requestTemplate.header(AUTHORIZATION, BEARER + token);
                }
            }
        };
    }

    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}