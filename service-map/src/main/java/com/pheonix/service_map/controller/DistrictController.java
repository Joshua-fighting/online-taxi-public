package com.pheonix.service_map.controller;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.service_map.service.InitDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {

    @Autowired
    private InitDistrictService initDistrictService;

    @GetMapping("/dic-district")
    public ResponseResult initDistrict(String keyWords){

        return initDistrictService.initDistrict(keyWords);
    }
}
