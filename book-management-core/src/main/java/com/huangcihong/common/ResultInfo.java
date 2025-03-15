package com.huangcihong.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultInfo<DATA> implements Serializable{
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private DATA data;

    public ResultInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResultInfo(String message) {
        this.code = 200;
        this.message = message;
    }
    public ResultInfo(String message, DATA data) {
        this.code = 200;
        this.message = message;
        this.data = data;
        checkData();
    }
    public ResultInfo(DATA data) {
        this.code = 200;
        this.message = "成功";
        this.data = data;
        checkData();
    }
    public ResultInfo(Integer code, String message, DATA data) {
        this.code = code;
        this.message = message;
        this.data = data;
        checkData();
    }

    private void checkData() {
        if(Objects.isNull(data)) {
            this.code = 404;
            this.message = "未找到资源";
        }else if(data instanceof String) {
            if(String.valueOf(data).isEmpty()) {
                this.code = 404;
                this.message = "未找到资源";
            }
        } else if (data instanceof Boolean) {
            if ((Boolean) data) {
                this.message = "成功";
            } else {
                this.message = "失败";
            }
        }
    }

    public static <DATA> ResultInfo<DATA> success() {
        return new ResultInfo<>(null);
    }

    public static <DATA> ResultInfo<DATA> success(DATA data) {
        return new ResultInfo<>(data);
    }
}
