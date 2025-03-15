package com.huangcihong.orm.config;

import com.mybatisflex.core.audit.AuditManager;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
@Slf4j
@MapperScan("com.huangcihong.**.repository")
@EnableTransactionManagement
public class MyBatisFlexConfiguration {


    public MyBatisFlexConfiguration() {
        //开启审计功能
        AuditManager.setAuditEnable(true);
        //设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> {
                    String url = "";
                    try {
                        url = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
                    } catch (Exception ignore) {

                    }
                    //System.out.println("uri: 【" + url + "】 took 【" + auditMessage.getElapsedTime() + " 】 ms >>> " + auditMessage.getFullSql());
                    log.info("uri: 【{}】 took 【{}】 ms >>> {}", url, auditMessage.getElapsedTime(), auditMessage.getFullSql());
                }
        );
    }

}

