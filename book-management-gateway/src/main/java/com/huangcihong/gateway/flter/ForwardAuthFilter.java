package com.huangcihong.gateway.flter;

import cn.dev33.satoken.same.SaSameUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author huangcihong
 */
@Component
public class ForwardAuthFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest newRequest = exchange.getRequest().mutate()
                .header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken()).build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();

        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("aaaaa")) {
            String providedApiKey = exchange.getRequest().getHeaders().getFirst(SaSameUtil.SAME_TOKEN);
            if (SaSameUtil.getToken().equals(providedApiKey)) {
                return chain.filter(exchange);
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(newExchange);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
