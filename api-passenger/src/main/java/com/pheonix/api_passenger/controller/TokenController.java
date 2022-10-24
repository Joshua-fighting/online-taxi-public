package com.pheonix.api_passenger.controller;

import com.pheonix.api_passenger.service.TokenService;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        log.info("旧refreshToken值:{}",refreshTokenSrc);
        return tokenService.getRefreshToken(refreshTokenSrc);
    }
}
