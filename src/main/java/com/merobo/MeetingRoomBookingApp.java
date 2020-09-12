package com.merobo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableMongoRepositories(basePackages = {"abm.authenticator.repositories", "com.merobo.repositories"})
@SpringBootApplication(scanBasePackages = {"com.merobo", "abm.contentmanager", "abm.authenticator",
        "com.abm.files"})
public class MeetingRoomBookingApp {

    public static void main(String[] args) {
        SpringApplication.run(MeetingRoomBookingApp.class, args);
    }

    @Bean
    public Docket meroboAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.merobo.resources"))
                .build();
    }

}