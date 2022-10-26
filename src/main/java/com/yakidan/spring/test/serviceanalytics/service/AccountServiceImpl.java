package com.yakidan.spring.test.serviceanalytics.service;

import com.yakidan.spring.test.serviceanalytics.entity.Account;
import com.yakidan.spring.test.serviceanalytics.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Long getAmount(UUID accountId) {
        Optional<Account> optionalAccount = repository.findById(accountId);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get().getBalance();
        }
        throw new RuntimeException("account this Id ${accountId} is not found");
    }

    @Transactional
    @Override
    public void addAmount(UUID id, Long value) {
        Account account = repository.findById(id).orElseThrow();
        account.setBalance(account.getBalance() + value);
        repository.save(account);
    }
}
