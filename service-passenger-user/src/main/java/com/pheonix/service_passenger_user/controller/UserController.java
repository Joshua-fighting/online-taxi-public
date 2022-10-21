package com.pheonix.service_passenger_user.controller;

import com.pheonix.internal_common.constant.CommonStatusEnum;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.VerificationCodeDTO;
import com.pheonix.service_passenger_user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegist(@RequestBody VerificationCodeDTO verificationCodeDTO){
        if(Objects.isNull(verificationCodeDTO)){
            return ResponseResult.fail(CommonStatusEnum.REQUEST_PARAM_CANNOT_BE_EMPTY.getCode(),CommonStatusEnum.REQUEST_PARAM_CANNOT_BE_EMPTY.getValue());
        }
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        log.info("手机号:{}",passengerPhone);
        userService.loginOrRegister(passengerPhone);
        return ResponseResult.success();
    }
}
