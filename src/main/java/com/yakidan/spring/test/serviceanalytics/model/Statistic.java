package com.yakidan.spring.test.serviceanalytics.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class Statistic {
    private long countResponsesInSecond = 0;

    @Override
    public String toString() {
        return "Count of response in second = " + countResponsesInSecond +
                ", Count of response in general = " + countResponsesGeneral;
    }

    private long countResponsesGeneral = 0;
}
