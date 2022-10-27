package com.pheonix.internal_common.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DirectionResponse {

    private Integer distance;

    private Integer duration;
}
