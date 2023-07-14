package com.example.productmicroservice.exceptions.type;

import com.example.productmicroservice.exceptions.AppException;
import com.example.productmicroservice.exceptions.ErrorCode;
import lombok.Getter;

/**
 * The TypeNotFoundException is an exception class that is thrown when a type is not found.
 * It extends the RuntimeException class and implements the AppException interface.
 * The exception includes an error code and parameters associated with the error.
 */
@Getter
public class TypeNotFoundException extends RuntimeException implements AppException {
    private ErrorCode errorCode;
    private Object[] params;

    public TypeNotFoundException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public TypeNotFoundException(String message) {
        super(message);
    }
}
