package com.pheonix.api_passenger.service.impl;

import com.pheonix.api_passenger.client.ServiceVerificationCodeClient;
import com.pheonix.api_passenger.service.VerificationCodeService;
import com.pheonix.internal_common.constant.CommonStatusEnum;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.response.NumberCodeResponse;
import com.pheonix.internal_common.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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

    /**
     * 生成验证码
     * @param passengerPhone
     * @return
     */
    @Override
    public ResponseResult generatorCode(String passengerPhone) {
        //调用验证码，获取验证码
        log.info("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        Integer numberCode = numberCodeResponse.getData().getNumberCode();
        log.info("numberCode = " + numberCode);
        //存入redis
        //key、value、过期时间
        String key = generateKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key,String.valueOf(numberCode),2, TimeUnit.MINUTES);

        //通过短信服务商，将对应的验证码发送到手机上
        //TODO
        return ResponseResult.success();
    }

    /**
     * 校验验证码
     * @param passengerPhone
     * @param verificationCode
     * @return
     */
    public ResponseResult checkCode(String passengerPhone,String verificationCode){
        //根据手机号，去redis读取验证码
        log.info("根据手机号，去redis读取验证码");

        //检验验证码
        String key = generateKeyByPhone(passengerPhone);
        String value = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isBlank(value)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_EXPIRED.getCode(),CommonStatusEnum.VERIFICATION_CODE_EXPIRED.getValue());
        }
        else if(!value.equals(verificationCode)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        //判断原来是否有用户，并进行对应的处理

        //颁发令牌

        TokenResponse tokenResponse = new TokenResponse("token value");
        return ResponseResult.success(tokenResponse);
    }

    //生成redis的key方法
     private String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
     }
}
