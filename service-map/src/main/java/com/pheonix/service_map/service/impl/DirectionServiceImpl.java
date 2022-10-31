package com.pheonix.service_map.service.impl;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import com.pheonix.internal_common.response.DirectionResponse;
import com.pheonix.service_map.client.MapDirectionClient;
import com.pheonix.service_map.service.DirectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DirectionServiceImpl implements DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    @Override
    public ResponseResult driving(ForecastPriceDTO forecastPriceDTO) {
        log.info("根据起点和终点的经纬度获取距离（米）和时长（分钟）");
        //调用第三方地图接口
        DirectionResponse direction = mapDirectionClient.direction(forecastPriceDTO.getDepLongitude(), forecastPriceDTO.getDepLatitude(), forecastPriceDTO.getDestLongitude(), forecastPriceDTO.getDestLatitude());
        return ResponseResult.success(direction);
    }
}
