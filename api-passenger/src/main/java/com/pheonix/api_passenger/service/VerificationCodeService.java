package com.pheonix.api_passenger.service;


import com.pheonix.internal_common.dto.ResponseResult;

public interface VerificationCodeService {

    ResponseResult generatorCode(String passengerPhone);
}
