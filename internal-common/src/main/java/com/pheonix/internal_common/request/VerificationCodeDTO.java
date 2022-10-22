package com.pheonix.internal_common.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VerificationCodeDTO {

    //乘客手机号码
    private String passengerPhone;

    //验证码
    private String verificationCode;

}
