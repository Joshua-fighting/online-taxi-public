package com.pheonix.api_passenger.service.impl;

import com.pheonix.api_passenger.service.VerificationCodeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Override
    public String generatorCode(String passengerPhone) {
        //调用验证码，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String code = "1111";
        //存入redis
        System.out.println("以下执行存入redis步骤");

        //返回值
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
