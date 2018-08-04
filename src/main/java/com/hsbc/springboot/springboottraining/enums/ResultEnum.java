package com.hsbc.springboot.springboottraining.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),

    FILE_NOT_EXIST(3, "文件不存在"),

    LOGIN_SUCCESS(5, "登录成功"),

    LOGOUT_SUCCESS(5, "登出成功"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}