package com.pheonix.api_passenger.service.impl;

import com.pheonix.api_passenger.service.TokenService;
import com.pheonix.internal_common.constant.CommonStatusEnum;
import com.pheonix.internal_common.constant.TokenConstant;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.dto.TokenResult;
import com.pheonix.internal_common.response.TokenResponse;
import com.pheonix.internal_common.utils.JwtUtils;
import com.pheonix.internal_common.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult getRefreshToken(String refreshTokenSrc) {
        //解析refreshToken
        try {
            TokenResult tokenResult = JwtUtils.parseToken(refreshTokenSrc);
            if(Objects.isNull(tokenResult)){
                return ResponseResult.fail(CommonStatusEnum.TOKEN_PARAM_ERROR.getCode(),CommonStatusEnum.TOKEN_PARAM_ERROR.getValue());
            }
            //读取redis中的refreshToken
            String refreshTokenKey = RedisUtils.genetatorTokenKey(tokenResult.getPhone(),tokenResult.getIdentity(),TokenConstant.REFRESH_TOKEN_TYPE);
            String refreshTokenValue = stringRedisTemplate.opsForValue().get(refreshTokenKey);
            //校验refreshToken
            if(StringUtils.isBlank(refreshTokenValue)||refreshTokenSrc.equals(refreshTokenValue)){
                return ResponseResult.fail(CommonStatusEnum.TOKEN_PARAM_ERROR.getCode(),CommonStatusEnum.TOKEN_PARAM_ERROR.getValue());
            }
            //生成双token
            Map<String,String> map = new HashMap<>();
            map.put(JwtUtils.JWT_KEY_PHONE,tokenResult.getPhone());
            map.put(JwtUtils.JWT_KEY_IDENTITY,tokenResult.getIdentity());
            map.put(JwtUtils.JWT_KEY_TOKEN_CONSTANT,TokenConstant.REFRESH_TOKEN_TYPE);
            String refreshToken = JwtUtils.generatorToken(map);
            map.put(JwtUtils.JWT_KEY_TOKEN_CONSTANT,TokenConstant.ACCESS_TOKEN_TYPE);
            String accessToken = JwtUtils.generatorToken(map);
            stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,8, TimeUnit.DAYS);
            stringRedisTemplate.opsForValue().set(RedisUtils.genetatorTokenKey(tokenResult.getPhone(),tokenResult.getIdentity(),TokenConstant.ACCESS_TOKEN_TYPE),accessToken,7,TimeUnit.DAYS);
            return ResponseResult.success(new TokenResponse(accessToken,refreshToken));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.fail(CommonStatusEnum.TOKEN_PARAM_ERROR.getCode(),CommonStatusEnum.TOKEN_PARAM_ERROR.getValue());
    }
}
