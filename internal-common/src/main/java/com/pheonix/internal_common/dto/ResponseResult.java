package com.pheonix.internal_common.dto;

import com.pheonix.internal_common.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    /**
     * 成功响应的方法
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData(data);
    }

    public static <T> ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData("");
    }

    /**
     * 默认失败方法
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setCode(CommonStatusEnum.FAIL.getCode())
                .setMessage(CommonStatusEnum.FAIL.getValue())
                .setData(data);
    }

    /**
     * 失败：自定义失败，错误码和提示信息
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 失败：自定义失败 错误码 和 提示信息、具体错误
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(int code,String message,T data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }


}
