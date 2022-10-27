package com.pheonix.internal_common.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ForecastPriceDTO {

    //出发地经度
    private String depLongitude;

    //出发地纬度
    private String depLatitude;

    //目的地经度
    private String destLongitude;

    //目的地纬度
    private String destLatitude;
}
