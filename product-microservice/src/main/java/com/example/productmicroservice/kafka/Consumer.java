package com.example.productmicroservice.kafka;

import com.example.productmicroservice.events.ProductBuyEvent;
import com.example.productmicroservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * This class represents a consumer that listens to Kafka topics and processes messages.
 */
@Slf4j
@Component
public class Consumer {
  private final ObjectMapper objectMapper;
  private final ProductService productService;

  @Autowired
  public Consumer(ObjectMapper objectMapper, ProductService productService) {
    this.objectMapper = objectMapper;
    this.productService = productService;
  }

  @KafkaListener(topics = "topic.customer.buy.product")
  public void processProductBuyEvent(String message) throws JsonProcessingException {
    log.info("message consumed {}", message);
    ProductBuyEvent productBuyEvent = objectMapper.readValue(message, ProductBuyEvent.class);
    productService.buyProduct(productBuyEvent);
  }
}