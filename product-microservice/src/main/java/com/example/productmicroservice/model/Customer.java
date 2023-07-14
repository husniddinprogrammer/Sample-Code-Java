package com.example.productmicroservice.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String passport;
    private LocalDate dateOfBirth;
    private Long productId;
    private BigDecimal balance;
}
