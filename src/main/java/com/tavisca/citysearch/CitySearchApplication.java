package com.tavisca.citysearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CitySearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitySearchApplication.class, args);
    }

    @Bean
    public RestTemplate defaultRestTemplate(RestTemplateBuilder templateBuilder) {
        return templateBuilder.build();
    }
}
