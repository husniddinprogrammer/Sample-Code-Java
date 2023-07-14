package com.example.customermicroservice.events;

import lombok.*;

/**
 * Represents a product buy event for kafka message.
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
