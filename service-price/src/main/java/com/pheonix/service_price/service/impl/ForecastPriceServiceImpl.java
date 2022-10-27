package com.pheonix.service_price.service.impl;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import com.pheonix.service_price.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Override
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
        log.info("调用地图服务，查询距离和时长");

        log.info("读取计价规则");

        log.info("根据距离、时长和计价规则、计算价格");
        return null;
    }
}
