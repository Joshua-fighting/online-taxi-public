package com.pheonix.api_passenger.controller;

import com.pheonix.internal_common.request.VerificationCodeDTO;
import com.pheonix.api_passenger.service.VerificationCodeService;
import com.pheonix.internal_common.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        log.info("传入手机参数:{}",passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult verificationCheckCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        log.info("手机号:{}  验证码:{}",passengerPhone,verificationCode);

        return verificationCodeService.checkCode(passengerPhone,verificationCode);
    }

}
