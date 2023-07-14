package com.example.customermicroservice.kafka;

import com.example.customermicroservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Represents a Kafka consumer component.
 * Consumer messages from the "topic.customer" Kafka topic.
 */
@Slf4j
@Component
public class Consumer {
  private final ObjectMapper objectMapper;
  private final CustomerService customerService;

  @Autowired
  public Consumer(ObjectMapper objectMapper,
                  CustomerService customerService) {
    this.objectMapper = objectMapper;
    this.customerService = customerService;
  }
}