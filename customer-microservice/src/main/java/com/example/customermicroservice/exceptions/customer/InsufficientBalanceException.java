package com.example.customermicroservice.exceptions.customer;

import com.example.customermicroservice.exceptions.AppException;
import com.example.customermicroservice.exceptions.ErrorCode;
import lombok.Getter;

/**
 * Exception thrown when there is insufficient balance in a customer.
 */
@Getter
public class InsufficientBalanceException extends RuntimeException implements AppException {
    private ErrorCode errorCode;
    private Object[] params;

    public InsufficientBalanceException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
