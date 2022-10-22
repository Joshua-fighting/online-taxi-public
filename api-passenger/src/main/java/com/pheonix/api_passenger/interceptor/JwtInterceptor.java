package com.pheonix.api_passenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.pheonix.internal_common.dto.ResponseResult;
import com.pheonix.internal_common.dto.TokenResult;
import com.pheonix.internal_common.utils.JwtUtils;
import com.pheonix.internal_common.utils.RedisUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";
        //解析token
        String token = request.getHeader("Authorization");
        try {
            TokenResult tokenResult = JwtUtils.parseToken(token);
            if(tokenResult == null){
                resultString = "tokrn invalid";
                result=false;
            }else{
                //从redis中取出token
                String phone = tokenResult.getPhone();
                String identity = tokenResult.getIdentity();
                //拼接redis的key值
                String tokenKey = RedisUtils.genetatorTokenKey(phone, identity);
                //从redis中取出token
                String tokenValue = stringRedisTemplate.opsForValue().get(tokenKey);
                if(StringUtils.isBlank(tokenValue)){
                    resultString = "token invalid";
                    result = false;
                }else{
                    if(!token.trim().equals(tokenValue.trim())){
                        resultString = "token invalid";
                        result = false;
                    }
                }
            }
        } catch (SignatureVerificationException e) {
            resultString = "token sign error";
            result = false;
        } catch (TokenExpiredException e) {
            resultString = "token time out";
            result = false;
        } catch (AlgorithmMismatchException e) {
            resultString = "token algorithmMismatchException";
            result = false;
        } catch (Exception e){
            resultString = "token invalid";
            result = false;
        }
        if(!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return true;
    }
}
