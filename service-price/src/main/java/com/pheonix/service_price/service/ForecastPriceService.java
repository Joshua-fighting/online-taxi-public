package com.pheonix.service_price.service;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;

public interface ForecastPriceService {

    /**
     * 根据出发地以及目的地地址来计算预估价值
     * @param forecastPriceDTO
     * @return
     */
    ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO);
}
