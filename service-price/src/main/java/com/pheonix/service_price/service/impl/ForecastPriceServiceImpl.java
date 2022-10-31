package com.pheonix.service_price.service.impl;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import com.pheonix.internal_common.response.DirectionResponse;
import com.pheonix.service_price.client.ServiceMapClient;
import com.pheonix.service_price.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Override
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
        log.info("调用地图服务，查询距离和时长");
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离：{} ，时长：{}",distance,duration);

        log.info("读取计价规则");

        log.info("根据距离、时长和计价规则、计算价格");
        return direction;
    }
}
