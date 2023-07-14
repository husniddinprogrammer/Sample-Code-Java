package com.example.productmicroservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.Map;


/**
 * Configuration class for Kafka related beans.
 * This class provides the necessary configurations for Kafka producers, consumers,
 * and other Kafka-related components.
 */
@Configuration
public class KafkaConfig {

  private final KafkaProperties kafkaProperties;

  @Autowired
  public KafkaConfig(KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> properties = kafkaProperties.buildProducerProperties();
    return new DefaultKafkaProducerFactory<>(properties);
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
    return new DefaultKafkaConsumerFactory<>(properties);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}