package com.huangcihong.common.entity.vo.auth;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenInfoVo {

    /**
     * 超时时间
     */
    private Long expire;

    /**
     * 时间
     */
    private Long timestamp;

    /**
     * 允许token
     */
    private String access_token;

    /**
     * 返回用户信息
     */
    private String userName;
}
