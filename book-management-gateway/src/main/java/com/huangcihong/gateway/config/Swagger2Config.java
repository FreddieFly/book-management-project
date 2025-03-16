package com.huangcihong.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author huangcihong
 */
@Configuration
@Slf4j
public class Swagger2Config {

    @RestController
    @RequestMapping("")
    public static class SwaggerController {

        @Autowired
        private DiscoveryClient discoveryClient;


        @GetMapping("swagger-resources")
        public Flux<SwaggerResource> swaggerResourceFlux() {
            return Flux.fromStream(discoveryClient.getServices().stream()
                    .filter(service ->  !"book-management-registry".equalsIgnoreCase(service) && !"book-management-gateway".equalsIgnoreCase(service))
                    .map(service -> discoveryClient.getInstances(service).stream().findFirst().orElse(null))
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparing(ServiceInstance::getServiceId))
                    .map(service -> {
                        SwaggerResource resource = new SwaggerResource();
                        resource.setName(service.getServiceId());
                        resource.setSwaggerVersion("2.0");
                        resource.setLocation("/" + service.getServiceId() + "/");
                        resource.setUrl("/" + service.getServiceId().toLowerCase() + "/v2/api-docs");
                        return resource;
                    }));
        }

        @GetMapping("swagger-resources/configuration/security")
        public Mono<Object> configurationSecurityMono() {
            return Mono.just(new HashMap<>(0));
        }

        @GetMapping("swagger-resources/configuration/ui")
        public Mono<UiConfiguration> configurationUiMono() {
            return Mono.just(
                    UiConfigurationBuilder.builder()
                            .build());
        }
    }
}
