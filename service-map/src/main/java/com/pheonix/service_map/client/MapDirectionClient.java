package com.pheonix.service_map.client;

import com.pheonix.internal_common.constant.AmapConfigConstants;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MapDirectionClient {

    @Value("${amap.key}")
    private String DIRECTION_KEY;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //组装请求参数url
        //https://restapi.amap.com/v3/direction/driving?origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=xml&key=<用户的key>
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(AmapConfigConstants.DIRECTION_URL);
        urlBuild.append("?" + "origin=" + depLongitude + "," + depLatitude +
                            "&destination=" + destLongitude + "," + destLatitude +
                            "&extensions=base&output=json&key=" + DIRECTION_KEY);
        log.info("调用高德地图接口，入参depLongitude={}，depLatitude={}，destLongitude={}，destLatitude={},key={}",depLongitude,depLatitude,destLongitude,destLatitude,DIRECTION_KEY);
        //调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        log.info("高德地图：路径规划，返回信息：" + directionEntity.getBody());
        //解析接口
        DirectionResponse directionResponse = parseDirectionEntity(directionEntity.getBody());

        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String directionStr){

        DirectionResponse directionResponse = new DirectionResponse();
        try {
            //最外层
            JSONObject result = JSONObject.fromObject(directionStr);
            if(result.has(AmapConfigConstants.STATUS)&&result.getInt(AmapConfigConstants.STATUS) == 1){
                if(result.has(AmapConfigConstants.ROUTE)){
                    JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                    JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                    JSONObject pathObject = pathsArray.getJSONObject(0);
                    if(pathObject.has(AmapConfigConstants.DISTANCE)){
                        directionResponse.setDistance(pathObject.getInt(AmapConfigConstants.DISTANCE));
                    }
                    if(pathObject.has(AmapConfigConstants.DURATION)){
                        directionResponse.setDuration(pathObject.getInt(AmapConfigConstants.DURATION));
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directionResponse;
    }
}
