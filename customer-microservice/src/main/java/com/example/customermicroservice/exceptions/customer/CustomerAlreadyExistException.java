package com.example.customermicroservice.exceptions.customer;

import com.example.customermicroservice.exceptions.AppException;
import com.example.customermicroservice.exceptions.ErrorCode;
import lombok.Getter;

/**
 * Exception thrown when a customer already exists.
 */
@Getter
public class CustomerAlreadyExistException extends RuntimeException implements AppException {
    private ErrorCode errorCode;
    private Object[] params;

    public CustomerAlreadyExistException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
