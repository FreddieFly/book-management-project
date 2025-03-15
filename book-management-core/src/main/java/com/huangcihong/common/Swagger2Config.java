package com.huangcihong.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class Swagger2Config {


	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(ApiInfo.DEFAULT)
				.enable(true)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.huangcihong"))
				.build()
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts());

	}

	private List<ApiKey> securitySchemes() {
		List<ApiKey> apiKeys = new ArrayList<>();
		apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
		return apiKeys;
	}

	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts = new ArrayList<>();
		securityContexts.add(SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("^(?!auth).*$")).build());
		return securityContexts;
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> securityReferences = new ArrayList<>();
		securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
		return securityReferences;
	}
}
