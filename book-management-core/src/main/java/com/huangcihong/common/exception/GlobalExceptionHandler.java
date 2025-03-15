package com.huangcihong.common.exception;

import com.huangcihong.common.ResultInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultInfo<Void> handleBusinessException(BusinessException e) {
        return new ResultInfo(e.getCode(), e.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo<Void> handleException(Exception e) {
        return new ResultInfo(500, "系统内部错误：" + e.getMessage());
    }
}