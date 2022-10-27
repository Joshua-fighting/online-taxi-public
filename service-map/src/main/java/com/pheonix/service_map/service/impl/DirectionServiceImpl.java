package com.pheonix.service_map.service.impl;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import com.pheonix.internal_common.response.DirectionResponse;
import com.pheonix.service_map.service.DirectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DirectionServiceImpl implements DirectionService {

    @Autowired
    DirectionService directionService;

    @Override
    public ResponseResult driving(ForecastPriceDTO forecastPriceDTO) {
        log.info("根据起点和终点的经纬度获取距离（米）和时长（分钟）");
        //调用地图接口

        DirectionResponse directionResponse = new DirectionResponse().setDistance(100).setDuration(5);
        return ResponseResult.success(directionResponse);
    }
}
