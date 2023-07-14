package com.example.customermicroservice.exceptions.customer;

import com.example.customermicroservice.exceptions.AppException;
import com.example.customermicroservice.exceptions.ErrorCode;
import lombok.Getter;

/**
 * Exception thrown when a customer is not found.
 */
@Getter
public class CustomerNotFoundException extends RuntimeException implements AppException {
    private ErrorCode errorCode;
    private Object[] params;

    public CustomerNotFoundException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
