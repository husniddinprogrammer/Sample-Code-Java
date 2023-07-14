package com.example.customermicroservice.model;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Long id;
    private String name;
    private BigDecimal cost;
    private Type type;
    private LocalDate addDate;
    private Long customerId;
}
