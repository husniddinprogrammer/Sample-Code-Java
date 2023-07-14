package com.example.productmicroservice.exceptions.product;

import com.example.productmicroservice.exceptions.AppException;
import com.example.productmicroservice.exceptions.ErrorCode;
import lombok.Getter;

/**
 * The ProductNotFoundException is an exception class that is thrown when a product is not found.
 * It extends the RuntimeException class and implements the AppException interface.
 * The exception includes an error code and parameters associated with the error.
 */
@Getter
public class ProductNotFoundException extends RuntimeException implements AppException {
    private ErrorCode errorCode;
    private Object[] params;

    public ProductNotFoundException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
