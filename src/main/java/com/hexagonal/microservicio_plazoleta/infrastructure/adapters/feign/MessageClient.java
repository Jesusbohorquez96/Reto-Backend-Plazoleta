package com.hexagonal.microservicio_plazoleta.infrastructure.adapters.feign;

import com.hexagonal.microservicio_plazoleta.application.dto.PhoneNumberResponse;
import com.hexagonal.microservicio_plazoleta.application.dto.VerifyCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.*;

@FeignClient(name = MICROSERVICES_MESSAGING, url = HTTP_MESSAGE)
public interface MessageClient {

    @PostMapping(CHECK)
    VerifyCodeResponse verifyCode( @RequestParam String phoneNumber,
                                   @RequestParam String code);

    @PostMapping(SEND)
    PhoneNumberResponse sendVerificationCode( @RequestParam String phoneNumber);
}
