package com.pheonix.api_passenger.service;

import com.pheonix.internal_common.dto.ResponseResult;

public interface TokenService {
    ResponseResult getRefreshToken(String refreshTokenSrc);
}
