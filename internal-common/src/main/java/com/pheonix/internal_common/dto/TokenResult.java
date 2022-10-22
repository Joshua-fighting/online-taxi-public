package com.pheonix.internal_common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenResult {

    private String phone;

    private String identity;

}
