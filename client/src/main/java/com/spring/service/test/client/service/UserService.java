package com.spring.service.test.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.UUID;

public class UserService {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);

    private final WebClient localApiClient;

    private static final String balanceVariable = "value";

    public UserService(WebClient localApiClient) {
        this.localApiClient = localApiClient;
    }

    public Long getAccount(UUID id) {
        return localApiClient
                .get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(Long.class)
                .block(REQUEST_TIMEOUT);
    }

    public Long postAccount(UUID id, long value) {
        return localApiClient
                .post()
                .uri("/" + id + "?" + balanceVariable + "=" + value)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Long.class)
                .block(REQUEST_TIMEOUT);
    }

}