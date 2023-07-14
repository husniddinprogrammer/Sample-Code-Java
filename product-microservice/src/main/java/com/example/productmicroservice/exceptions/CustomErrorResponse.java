package com.example.productmicroservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The CustomErrorResponse class represents a custom error response.
 * It contains the error message, error code, and the timestamp when the error occurred.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomErrorResponse {
  private String message;
  private String code;
  private LocalDateTime time;
}