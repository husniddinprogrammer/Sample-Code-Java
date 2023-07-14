package com.example.productmicroservice.exceptions;

/**
 * The AppException interface represents an application exception.
 * It defines methods to retrieve the error code and parameters associated with the exception.
 */
public interface AppException {
  ErrorCode getErrorCode();

  Object[] getParams();
}