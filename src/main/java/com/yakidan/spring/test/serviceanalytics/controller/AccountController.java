package com.yakidan.spring.test.serviceanalytics.controller;

import com.yakidan.spring.test.serviceanalytics.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    AccountService service;

    @GetMapping("/{id}")
    Long getAccountById(@PathVariable UUID id) {
        return service.getAmount(id);
    }

    @PostMapping("/{id}")
    void getAccountById(@PathVariable UUID id, @RequestParam("value") Long value) {
        service.addAmount(id, value);
    }
}
