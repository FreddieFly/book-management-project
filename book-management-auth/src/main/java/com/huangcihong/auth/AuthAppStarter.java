package com.huangcihong.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan("com.huangcihong")
@EnableFeignClients(basePackages = "com.huangcihong")
public class AuthAppStarter {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthAppStarter.class).run(args);
    }

    @Bean
    public CommandLineRunner initDatabase(DataSource dataSource) {
        return args -> {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("user.sql"));
            populator.execute(dataSource);
        };
    }
}
