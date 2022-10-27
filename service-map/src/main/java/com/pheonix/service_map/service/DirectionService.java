package com.pheonix.service_map.service;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;

public interface DirectionService {
    /**
     * 根据起点和终点的经纬度获取距离（米）和时长（分钟）
     * @param forecastPriceDTO
     * @return
     */
    ResponseResult driving(ForecastPriceDTO forecastPriceDTO);
}
