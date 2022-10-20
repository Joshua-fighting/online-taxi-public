package com.pheonix.serviceverificationcode.controller;

import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.response.NumberCodeResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size")int size){
        if(size<=1){
            return ResponseResult.fail("size too short!");
        }
        log.info("size={}",size);
        //生成验证码
        Integer code = getCode(size);
        //定义返回值
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse().setNumberCode(code);
        return ResponseResult.success(numberCodeResponse);
    }

    private Integer getCode(int size){
        //获取随机数
        double mathRandom = (Math.random()*9+1)*(Math.pow(10,size-1));
        int resultInt = (int)mathRandom;
        return resultInt;
    }

}
