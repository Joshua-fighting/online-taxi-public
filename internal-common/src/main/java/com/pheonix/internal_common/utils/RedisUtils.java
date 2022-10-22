package com.pheonix.internal_common.utils;

public class RedisUtils {

    //乘客验证码的前缀
    private static String verificationCodePrefix = "passenger-verification-code-";

    //验证码的前缀
    private static String tokenPrefix = "token-";


    //生成redis的key方法
    public static String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份标识，生成Token
     * @param phone
     * @param identity
     * @return
     */
    public static String genetatorTokenKey(String phone,String identity){
        return tokenPrefix + phone + "-" + identity;
    }
}
