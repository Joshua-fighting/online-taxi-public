package com.pheonix.api_passenger.client;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {

    @GetMapping("/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);
}
