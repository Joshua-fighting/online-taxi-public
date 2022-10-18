package com.pheonix.serviceverificationcode.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public String numberCode(@PathVariable("size")int size){
        log.info("size={}",size);
        JSONObject result = new JSONObject();
        result.put("code","1");
        result.put("message","success");
        JSONObject data = new JSONObject();
        data.put("numberCode",123456);
        result.put("data",data);
        return result.toString();
    }
}
