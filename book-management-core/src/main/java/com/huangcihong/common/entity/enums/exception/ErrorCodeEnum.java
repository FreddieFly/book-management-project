package com.huangcihong.common.entity.enums.exception;

/**
 * 错误码枚举类
 */
public enum ErrorCodeEnum {

    USERNAME_ALREADY_EXISTS(4001, "用户名已存在"),
    USER_NOT_FOUND(4002, "用户不存在"),
    INTERNAL_SERVER_ERROR(500, "系统内部错误");

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}