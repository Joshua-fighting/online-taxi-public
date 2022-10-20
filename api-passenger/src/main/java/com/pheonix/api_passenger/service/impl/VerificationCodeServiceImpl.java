package com.pheonix.api_passenger.service.impl;

import com.pheonix.api_passenger.client.ServiceVerificationCodeClient;
import com.pheonix.api_passenger.service.VerificationCodeService;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.response.NumberCodeResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    ServiceVerificationCodeClient serviceVerificationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String verificationCodePrefix = "passenger-verification-code-";

    @Override
    public ResponseResult generatorCode(String passengerPhone) {
        //调用验证码，获取验证码
        log.info("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        Integer numberCode = numberCodeResponse.getData().getNumberCode();
        log.info("numberCode = " + numberCode);
        //存入redis
        //key、value、过期时间
        String key = verificationCodePrefix + passengerPhone;
        stringRedisTemplate.opsForValue().set(key,String.valueOf(numberCode),2, TimeUnit.MINUTES);

        //通过短信服务商，将对应的验证码发送到手机上
        //TODO
        return ResponseResult.success();
    }
}
