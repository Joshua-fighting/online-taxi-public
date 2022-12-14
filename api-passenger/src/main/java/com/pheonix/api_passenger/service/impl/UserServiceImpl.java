package com.pheonix.api_passenger.service.impl;

import com.pheonix.api_passenger.client.ServicePassengerUserClient;
import com.pheonix.api_passenger.service.UserService;
import com.pheonix.internal_common.dto.PassengerUser;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.dto.TokenResult;
import com.pheonix.internal_common.request.VerificationCodeDTO;
import com.pheonix.internal_common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ServicePassengerUserClient passengerUserClient;

    @Override
    public ResponseResult getUser(String accessToken) {
        log.info("accessToken:{}",accessToken);
        //解析Token，拿到手机号
        TokenResult tokenResult = JwtUtils.parseToken(accessToken);
        String phone = tokenResult.getPhone();
        //根据手机号查询用户信息
        ResponseResult user = passengerUserClient.getUser(phone);
        return ResponseResult.success(user.getData());
    }
}
