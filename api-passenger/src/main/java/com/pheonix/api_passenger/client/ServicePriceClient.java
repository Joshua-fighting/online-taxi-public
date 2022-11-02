package com.pheonix.api_passenger.client;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-price")
public interface ServicePriceClient {

    @PostMapping("/forecast-price")
    ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
