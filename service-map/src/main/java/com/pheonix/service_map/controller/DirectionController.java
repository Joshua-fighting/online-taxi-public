package com.pheonix.service_map.controller;

import com.pheonix.internal_common.constant.CommonStatusEnum;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/direction")
public class DirectionController {

    @GetMapping("/driving")
    public ResponseResult driving(@RequestBody ForecastPriceDTO forecastPriceDTO){
        if(Objects.isNull(forecastPriceDTO)){
            return ResponseResult.fail(CommonStatusEnum.REQUEST_PARAM_CANNOT_BE_EMPTY.getCode(),CommonStatusEnum.REQUEST_PARAM_CANNOT_BE_EMPTY.getValue());
        }

        return ResponseResult.success();
    }
}
