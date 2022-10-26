package com.yakidan.spring.test.serviceanalytics.aspect;

import com.yakidan.spring.test.serviceanalytics.service.StatisticService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Autowired
    private StatisticService service;

    @After("com.yakidan.spring.test.serviceanalytics.aspect.pointcut.LoggingPointcut.allAccountServiceMethods()")
    public void afterReturning() {
        service.updateResponseStatistic();
    }

}
