package com.huangcihong.book;

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
public class BookAppStarter {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BookAppStarter.class).run(args);
    }

    @Bean
    public CommandLineRunner initDatabase(DataSource dataSource) {
        return args -> {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("book.sql"));
            populator.addScript(new ClassPathResource("book_borrow.sql"));
            populator.execute(dataSource);
        };
    }
}
