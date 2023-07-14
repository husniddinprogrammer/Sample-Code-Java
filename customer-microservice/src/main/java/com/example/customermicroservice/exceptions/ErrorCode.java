package com.example.customermicroservice.exceptions;

import com.example.customermicroservice.constants.ErrorCodeConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents error codes for specific application exceptions.
 * Each enum value contains a code, HttpStatus, and LocalDateTime.
 */
@Getter
public enum ErrorCode {
  CUSTOMER_ALREADY_EXIST(ErrorCodeConstants.CUSTOMER_ALREADY_EXIST_409,
            HttpStatus.CONFLICT,
            LocalDateTime.now()),
  CUSTOMER_NOT_FOUND(ErrorCodeConstants.CUSTOMER_NOT_FOUND_401,
            HttpStatus.UNAUTHORIZED,
            LocalDateTime.now()),
  CUSTOMER_INSUFFICIENT_BALANCE(ErrorCodeConstants.CUSTOMER_INSUFFICIENT_BALANCE_402,
            HttpStatus.PAYMENT_REQUIRED,
            LocalDateTime.now());

  private final String code;
  private final HttpStatus httpStatus;
  private final LocalDateTime localDateTime;

  ErrorCode(String code, HttpStatus httpStatus, LocalDateTime localDateTime) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.localDateTime = localDateTime;
  }
}