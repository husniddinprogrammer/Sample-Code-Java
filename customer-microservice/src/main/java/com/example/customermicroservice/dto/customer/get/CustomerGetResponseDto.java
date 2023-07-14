package com.example.customermicroservice.dto.customer.get;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data transfer object representing the response for getting a customer.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerGetResponseDto {
    private String firstName;
    private String lastName;
    private String passport;
    private LocalDate dateOfBirth;
    private Long productId;
    private BigDecimal balance;
}
