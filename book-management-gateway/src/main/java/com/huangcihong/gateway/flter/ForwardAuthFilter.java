package com.huangcihong.gateway.flter;

import cn.dev33.satoken.same.SaSameUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器，为请求添加 Same-Token 
 */
@Component
public class ForwardAuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest newRequest = exchange.getRequest().mutate()
                .header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken()).build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();

        return chain.filter(newExchange);
    }


}
