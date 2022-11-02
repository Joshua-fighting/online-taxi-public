package com.pheonix.service_price.service.impl;

import com.pheonix.internal_common.constant.CommonStatusEnum;
import com.pheonix.internal_common.dto.PriceRule;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.request.ForecastPriceDTO;
import com.pheonix.internal_common.response.DirectionResponse;
import com.pheonix.internal_common.response.ForecastPriceResponse;
import com.pheonix.service_price.client.ServiceMapClient;
import com.pheonix.service_price.mapper.PriceRuleMapper;
import com.pheonix.service_price.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ForecastPriceServiceImpl implements ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    PriceRuleMapper priceRuleMapper;

    @Override
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
        log.info("调用地图服务，查询距离和时长");
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离(m)：{} ，时长(s)：{}",distance,duration);

        log.info("读取计价规则");
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("city_code","110000");
        queryMap.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if(CollectionUtils.isEmpty(priceRules)||priceRules.size()<=0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_IS_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_IS_EMPTY.getValue());
        }
        log.info("根据距离、时长和计价规则、计算价格");
        Double price = getPrice(distance, duration, priceRules.get(0));
        return ResponseResult.success(new ForecastPriceResponse().setPrice(price));
    }

    /**
     * 根据距离、时长 和计价规则，计算最终价格
     * @param distance
     * @param duration
     * @param priceRule
     * @return
     */
    private Double getPrice(Integer distance,Integer duration,PriceRule priceRule){
        BigDecimal price = new BigDecimal(0);

        //起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);

        //里程费
        BigDecimal distanceDecimal = new BigDecimal(distance);//总里程
        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);//总里程KM
        //起步里程
        BigDecimal startMileDecimal = new BigDecimal(priceRule.getStartMile());
        double distanceSubtract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
        //最终收费的里程数
        BigDecimal mileDecimal = new BigDecimal(distanceSubtract < 0 ? 0 : distanceSubtract);

        //计程单价 元/km
        BigDecimal unitPricePerMileDecimal = new BigDecimal(priceRule.getUnitPricePerMile());

        //里程价格
        BigDecimal mileFare = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(mileFare);

        //时长费的计算
        BigDecimal time = new BigDecimal(duration);//时长（秒）
        //分钟数
        BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal unitPricePerMinuteDecimal = new BigDecimal(priceRule.getUnitPricePerMinute());

        price = price.add(timeDecimal.multiply(unitPricePerMinuteDecimal)).setScale(2,BigDecimal.ROUND_HALF_UP);

        return price.doubleValue();
    }
}
