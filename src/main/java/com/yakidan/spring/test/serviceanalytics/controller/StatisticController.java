package com.yakidan.spring.test.serviceanalytics.controller;

import com.yakidan.spring.test.serviceanalytics.model.Statistic;
import com.yakidan.spring.test.serviceanalytics.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticController {
    @Autowired
    private StatisticService service;

    @GetMapping()
    public Statistic getStatistic() {
        return service.getStatistic();
    }

    @DeleteMapping()
    public Statistic deleteStatistic() {
        return service.clearInfo();
    }
}
