package com.pheonix.service_price.client;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import com.pheonix.internal_common.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-map")
public interface ServiceMapClient {

    @GetMapping("/direction/driving")
    ResponseResult<DirectionResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
