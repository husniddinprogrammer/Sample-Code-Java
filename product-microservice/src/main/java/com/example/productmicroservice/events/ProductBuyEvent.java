package com.example.productmicroservice.events;

import lombok.*;

/**
 * Data Transfer Object (DTO) for a product event.
 * This class represents the payload for a product event that is sent to Kafka.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductBuyEvent {
    private Long customerId;
    private String productName;
}
