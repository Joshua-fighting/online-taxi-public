package com.pheonix.api_passenger.request;

import lombok.Data;

@Data
public class VerificationCodeDTO {

    //乘客手机号码
    private String passengerPhone;

    //验证码
    private String verificationCode;

}
