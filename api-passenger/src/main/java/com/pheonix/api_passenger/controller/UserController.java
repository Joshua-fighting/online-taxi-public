package com.pheonix.api_passenger.controller;

import com.pheonix.api_passenger.service.UserService;
import com.pheonix.internal_common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUsersInfo")
    public ResponseResult getUser(HttpServletRequest request){
        //从http请求中获取 accessToken
        String accessToken = request.getHeader("Authorization");
        return userService.getUser(accessToken);
    }

}
