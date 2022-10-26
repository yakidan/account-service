package com.yakidan.spring.test.serviceanalytics.service;

import com.yakidan.spring.test.serviceanalytics.model.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatisticService {
    @Autowired
    private Statistic statistic;

    private final static int TIME_FIXED = 1000;
    private static final Logger log = LoggerFactory.getLogger(StatisticService.class);

    public synchronized void updateResponseStatistic() {
        statistic.setCountResponsesInSecond(statistic.getCountResponsesInSecond() + 1);
        statistic.setCountResponsesGeneral(statistic.getCountResponsesGeneral() + 1);
    }


    @Scheduled(fixedDelay = TIME_FIXED)
    public synchronized void printStatistic() {
        log.info("statistics response = {}", statistic);
        statistic.setCountResponsesInSecond(0);
    }

    public Statistic getStatistic() {
        return this.statistic;
    }

    synchronized public Statistic clearInfo() {
        statistic.setCountResponsesInSecond(0);
        statistic.setCountResponsesGeneral(0);
        return statistic;
    }

}
