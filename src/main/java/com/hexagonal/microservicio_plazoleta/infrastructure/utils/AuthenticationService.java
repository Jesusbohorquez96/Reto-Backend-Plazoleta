package com.hexagonal.microservicio_plazoleta.infrastructure.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@Service
public class AuthenticationService {

    public Long getAuthenticatedUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null || !(principal instanceof String)) {
            throw new IllegalArgumentException(USER_NOT_AUTHENTICATED);
        }
        try {
            return Long.valueOf((String) principal);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(USER_ID_INVALID);
        }
    }
}
