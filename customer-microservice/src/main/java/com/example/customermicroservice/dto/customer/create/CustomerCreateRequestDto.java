package com.example.customermicroservice.dto.customer.create;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data transfer object representing the request for creating a customer.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerCreateRequestDto {
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 15, message = "First name should be between 3 and 15 characters")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 15, message = "Last name should be between 3 and 15 characters")
    private String lastName;
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid passport format")
    @NotBlank(message = "Passport is required")
    @Size(min = 7, max = 10, message = "Passport should be between 7 and 10 characters")
    private String passport;
    @Past(message = "Date of birth should be in the past")
    private LocalDate dateOfBirth;
    private Long productId;
    private BigDecimal balance;
}
