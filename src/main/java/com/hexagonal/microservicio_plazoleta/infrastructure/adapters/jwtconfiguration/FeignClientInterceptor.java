package com.hexagonal.microservicio_plazoleta.infrastructure.adapters.jwtconfiguration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        template.header(AUTHORIZATION, BEARER + token);
    }
}
