package com.pheonix.internal_common.constant;


public enum CommonStatusEnum {

    SUCCESS(1,"success"),
    FAIL(0,"fail"),
    //验证码问题
    VERIFICATION_CODE_ERROR(1001,"验证码错误"),
    VERIFICATION_CODE_EXPIRED(1002,"验证码已过期"),
    //请求参数以及返回值问题
    REQUEST_PARAM_CANNOT_BE_EMPTY(1050,"请求参数不能为空"),
    RESULT_PARAM_IS_EMPTY(1051,"返回值为空"),
    //token问题
    TOKEN_PARAM_ERROR(1101,"token解析错误"),
    //用户问题
    USER_IS_EMPTY(1200,"查询不到该用户信息"),

    //收费问题
    PRICE_RULE_IS_EMPTY(1500,"计价规则为空")
    ;


    private int code;
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
