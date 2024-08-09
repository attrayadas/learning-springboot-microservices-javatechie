package com.attraya.config;

import com.attraya.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;

// Will be used to track the rows that are creating problem
@Slf4j
public class BatchStepEventListener implements SkipListener<Customer, Number> {
    @Override
    public void onSkipInRead(Throwable throwable) {
        log.info("A failure on read {}", throwable.getMessage());
    }

    @Override
    public void onSkipInWrite(Number number, Throwable throwable) {
        log.info("A failure on write {}", throwable.getMessage());
    }

    @SneakyThrows
    @Override
    public void onSkipInProcess(Customer customer, Throwable throwable) {
        log.info("Customer value {} was skipped due to the exception {}", new ObjectMapper().writeValueAsString(customer), throwable.getMessage());
    }
}
