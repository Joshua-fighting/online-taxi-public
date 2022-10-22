package com.pheonix.api_passenger.client;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @PostMapping("/user")
    ResponseResult loginOrRegist(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
