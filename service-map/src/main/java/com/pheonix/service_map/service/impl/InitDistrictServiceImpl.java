package com.pheonix.service_map.service.impl;

import com.pheonix.internal_common.constant.AmapConfigConstants;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.service_map.client.MapDicDistrictClient;
import com.pheonix.service_map.service.InitDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InitDistrictServiceImpl implements InitDistrictService {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Override
    public ResponseResult initDistrict(String keywords) {
        //请求地图
        String dicDistrict = mapDicDistrictClient.dicDistrict(keywords);
        //解析结果

        //插入数据库

        return ResponseResult.success(dicDistrict);
    }
}
