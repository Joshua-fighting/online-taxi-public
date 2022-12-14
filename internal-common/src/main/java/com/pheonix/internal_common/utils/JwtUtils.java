package com.pheonix.internal_common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pheonix.internal_common.dto.TokenResult;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    //盐
    private static final String SIGN = "LSYJOSHUA0021!@";

    public static final String JWT_KEY_PHONE = "passengerPhone";

    //乘客是1，用户是2
    public static final String JWT_KEY_IDENTITY = "identity";

    public static final String JWT_KEY_TOKEN_CONSTANT = "tokenType";

    public static final String JWT_TOKEN_TIME = "tokenTime";

    //生成Token
    public static String generatorToken(Map<String,String> map){
        //防止每次生成的token一样
        map.put(JWT_TOKEN_TIME,Calendar.getInstance().getTime().toString());

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
        String phone = "";
        String identity = "";
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
            phone = verify.getClaim(JWT_KEY_PHONE).asString();
            identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
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
