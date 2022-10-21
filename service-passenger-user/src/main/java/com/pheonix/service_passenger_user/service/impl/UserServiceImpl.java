package com.pheonix.service_passenger_user.service.impl;

import com.pheonix.internal_common.constant.CommonStatusEnum;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.service_passenger_user.dto.PassengerUser;
import com.pheonix.service_passenger_user.mapper.PassengerUserMapper;
import com.pheonix.service_passenger_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PassengerUserMapper passengerUserMapper;

    @Override
    public ResponseResult loginOrRegister(String passengerPhone) {
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if(CollectionUtils.isEmpty(passengerUsers)){
            return ResponseResult.fail(CommonStatusEnum.RESULT_PARAM_IS_EMPTY.getCode(),CommonStatusEnum.RESULT_PARAM_IS_EMPTY.getValue());
        }
        //判断用户信息是否存在


        return ResponseResult.success();
    }
}
