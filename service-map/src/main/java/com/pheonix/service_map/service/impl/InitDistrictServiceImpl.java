package com.pheonix.service_map.service.impl;

import com.pheonix.internal_common.constant.AmapConfigConstants;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.service_map.service.InitDistrictService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InitDistrictServiceImpl implements InitDistrictService {

    @Value("${amap.key}")
    private String amapKey;

    @Override
    public ResponseResult initDistrict(String keywords) {
        //https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
        //拼接请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?keywords="+keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&key="+amapKey);
        //解析结果

        //插入数据库

        return ResponseResult.success();
    }
}
