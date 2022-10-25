package com.pheonix.api_passenger.service;

import com.pheonix.internal_common.dto.ResponseResult;

public interface UserService {
    ResponseResult getUser(String accessToken);
}
