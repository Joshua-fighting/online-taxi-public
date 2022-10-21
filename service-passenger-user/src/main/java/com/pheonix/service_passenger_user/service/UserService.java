package com.pheonix.service_passenger_user.service;

import com.pheonix.internal_common.dto.ResponseResult;

public interface UserService {

    ResponseResult loginOrRegister(String passengerPhone);
}
