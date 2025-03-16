package com.huangcihong.common.entity.vo.result;

/**
 * 统一返回结果封装
 */
public class ResultInfo<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ResultInfo<T> success(T data) {
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    public static <T> ResultInfo<T> success() {
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(200);
        result.setMessage("成功");
        return result;
    }

    public static <T> ResultInfo<T> fail(int code, String message) {
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}