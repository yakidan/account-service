package com.yakidan.spring.test.serviceanalytics.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "account")
public class Account {

    @Id
    private UUID id;

    @Column(name = "balance")
    private long balance;

}
