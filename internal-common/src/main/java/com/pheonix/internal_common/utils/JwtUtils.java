package com.pheonix.internal_common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pheonix.internal_common.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    //盐
    private static final String SIGN = "LSYJOSHUA0021!@";

    public static final String JWT_KEY_PHONE = "passengerPhone";

    //乘客是1，用户是2
    public static final String JWT_KEY_IDENTITY = "identity";

    //生成Token
    public static String generatorToken(Map<String,String> map){
        //token过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,1);
        Date date = instance.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整合map
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //整合过期时间
        //builder.withExpiresAt(date);
        //生成 token，加盐
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }

    //解析Token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();

        return new TokenResult().setPhone(phone).setIdentity(identity);
    }



    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,"13576845354");
        map.put(JWT_KEY_IDENTITY,"1");
        String s = generatorToken(map);
        System.out.println("生成的Token = " + s);
        TokenResult tokenResult = parseToken(s);
        System.out.println("手机号："+tokenResult.getPhone());
        System.out.println("身份："+tokenResult.getIdentity());
    }

}
