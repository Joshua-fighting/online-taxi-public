package com.pheonix.service_price.controller;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import com.pheonix.service_price.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastController {

    @Autowired
    ForecastPriceService forecastPriceService;

    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){

        return forecastPriceService.forecastPrice(forecastPriceDTO);
    }
}
