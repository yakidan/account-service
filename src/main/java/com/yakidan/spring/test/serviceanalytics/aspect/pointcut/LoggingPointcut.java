package com.yakidan.spring.test.serviceanalytics.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointcut {
    @Pointcut("execution(* com.yakidan.spring.test.serviceanalytics.service.AccountService.*(..))")
    public void allAccountServiceMethods() {}
}
