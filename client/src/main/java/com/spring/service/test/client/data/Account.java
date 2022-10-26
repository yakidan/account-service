package com.spring.service.test.client.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


@Data
@AllArgsConstructor
public class Account {

    private UUID id;

    private long balance;

}
