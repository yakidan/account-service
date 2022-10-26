package com.spring.service.test.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    @Scope("prototype")
    public WebClient localApiClient() {
        return WebClient.create("http://localhost:8080/api/v1/account");
    }
}
