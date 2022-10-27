package com.pheonix.api_passenger.controller;

import com.pheonix.api_passenger.service.ForecastPriceService;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ForecastPriceController {

    @Autowired
    ForecastPriceService forecastPriceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){
        log.info("出发地经度：{},出发地纬度：{},目的地经度：{},目的地纬度：{}",forecastPriceDTO.getDepLongitude(),forecastPriceDTO.getDepLatitude(),forecastPriceDTO.getDestLongitude(),forecastPriceDTO.getDestLatitude());
        return forecastPriceService.forecastPrice(forecastPriceDTO);
    }
}
