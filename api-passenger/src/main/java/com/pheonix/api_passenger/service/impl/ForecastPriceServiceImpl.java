package com.pheonix.api_passenger.service.impl;

import com.pheonix.api_passenger.service.ForecastPriceService;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Override
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
        //调用计价服务，计算价格

        return null;
    }
}
