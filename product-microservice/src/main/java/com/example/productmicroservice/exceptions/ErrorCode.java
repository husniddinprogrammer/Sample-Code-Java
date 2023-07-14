package com.example.productmicroservice.exceptions;

import com.example.productmicroservice.constants.ErrorCodeConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * The ErrorCode enum represents the error codes and corresponding HTTP status for
 * different error scenarios.
 * Each error code is associated with a unique code, HTTP status, and timestamp
 * when the error occurred.
 */
@Getter
public enum ErrorCode {
  PRODUCT_ALREADY_EXIST(ErrorCodeConstants.PRODUCT_ALREADY_EXIST_409,
            HttpStatus.CONFLICT,
            LocalDateTime.now()),
  PRODUCT_NOT_FOUND(ErrorCodeConstants.PRODUCT_NOT_FOUND_404,
            HttpStatus.NOT_FOUND,
            LocalDateTime.now()),
  TYPE_NOT_FOUND(ErrorCodeConstants.TYPE_ALREADY_EXIST_409,
            HttpStatus.NOT_FOUND,
            LocalDateTime.now()),
  TYPE_ALREADY_EXIST(ErrorCodeConstants.TYPE_NOT_FOUND_404,
            HttpStatus.CONFLICT,
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