package com.huangcihong.gateway.config;

import cn.dev33.satoken.same.SaSameUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AuthSameTokenRefreshTask {
    // 从 0 分钟开始 每隔 5 分钟执行一次 Same-Token
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void refreshToken() {
        SaSameUtil.refreshToken();
    }
}
