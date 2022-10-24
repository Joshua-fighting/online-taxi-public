package com.pheonix.internal_common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {

    private String accessToken;

    private String refreshToken;

    public TokenResponse(String accessToken,String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
