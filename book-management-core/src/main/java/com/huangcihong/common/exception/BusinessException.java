package com.huangcihong.common.exception;

import com.huangcihong.common.enums.ErrorCodeEnum;

/**
 * 自定义业务异常
 */
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}