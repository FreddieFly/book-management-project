package com.huangcihong.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author huangcihong
 */
@SpringBootApplication
@ComponentScan("com.huangcihong")
public class GatewayServerStarter {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayServerStarter.class).run(args);
    }

}
