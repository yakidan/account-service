package com.yakidan.spring.test.serviceanalytics.service;

import java.util.UUID;

public interface AccountService
{
    /**
     * Retrieves current balance or zero if addAmount() method was not called before fsor pecified id
     *
     * @param id balance identifier
     */
    Long getAmount(UUID id);

    /**
     * Increases balance or set if addAmount() method was called first time
     *
     * @param id balance identifier
     * @param value positive or negative value, which must be added to current balance
     */
    void addAmount(UUID id, Long value);
}