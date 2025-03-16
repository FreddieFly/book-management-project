package com.huangcihong.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.huangcihong.common.entity.vo.result.ResultInfo;
import com.huangcihong.common.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultInfo<Void> handleBusinessException(BusinessException e) {
        return ResultInfo.fail(e.getCode(), e.getMessage());
    }


    /**
     * 处理其他异常
     */
    @ExceptionHandler(NotLoginException.class)
    public ResultInfo<Void> handleNotLoginExceptionException(Exception e) {
        log.error(e.getMessage(),e);
        return ResultInfo.fail(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo<Void> handleException(Exception e) {
        log.error(e.getMessage(),e);
        return ResultInfo.fail(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
    }
}