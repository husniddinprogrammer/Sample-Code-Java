package com.example.customermicroservice.exceptions;

public interface AppException {
  ErrorCode getErrorCode();

  Object[] getParams();
}
