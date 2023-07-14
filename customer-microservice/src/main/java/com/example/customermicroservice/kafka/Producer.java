package com.example.customermicroservice.kafka;

import com.example.customermicroservice.events.ProductBuyEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Represents a Kafka producer component.
 * Send messages to Kafka topics "CustomerTopic".
 */
@Slf4j
@Component
public class Producer {
  @Value("${topic.name}")
  private String buyProductTopic;

  private final ObjectMapper objectMapper;
  private final KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  public Producer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  public String sendMessageProductBuy(ProductBuyEvent productBuyEvent)
          throws JsonProcessingException {
    String buyEventAsMessage = objectMapper.writeValueAsString(productBuyEvent);
    kafkaTemplate.send(buyProductTopic, buyEventAsMessage);
    log.info("Customer request produced {}", buyEventAsMessage);
    return "message sent";
  }
}