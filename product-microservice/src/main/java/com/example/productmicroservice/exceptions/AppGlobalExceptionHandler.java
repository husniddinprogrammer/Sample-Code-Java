package com.example.productmicroservice.exceptions;

import com.example.productmicroservice.exceptions.product.ProductAlreadyExistException;
import com.example.productmicroservice.exceptions.product.ProductNotFoundException;
import com.example.productmicroservice.exceptions.type.TypeAlreadyExistException;
import com.example.productmicroservice.exceptions.type.TypeNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The AppGlobalExceptionHandler class handles global exceptions in the application.
 * It provides exception handling for validation errors and custom application exceptions.
 */
@Component
@RestControllerAdvice(basePackages = "com.example.productmicroservice")
public class AppGlobalExceptionHandler {
  private final MessageSource messageSource;

  @Autowired
  public AppGlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(ConstraintViolationException ex) {
    BindingResult bindingResult = new BeanPropertyBindingResult(ex, "constraintViolationException");
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      String field = violation.getPropertyPath().toString();
      String errorMessage = violation.getMessage();
      bindingResult.addError(new FieldError("constraintViolationException", field, errorMessage));
    }
    Map<String, String> errors = new HashMap<>();
    for (ObjectError error : bindingResult.getAllErrors()) {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler({ProductAlreadyExistException.class, ProductNotFoundException.class,
                     TypeAlreadyExistException.class, TypeNotFoundException.class})
  public ResponseEntity<CustomErrorResponse> handleProjectException(AppException ex) {
    CustomErrorResponse apiResponse = new CustomErrorResponse();
    apiResponse.setCode(ex.getErrorCode().getCode());
    apiResponse.setMessage(messageSource.getMessage(ex.getErrorCode().getCode(),
                ex.getParams(),
                LocaleContextHolder.getLocale()));
    apiResponse.setTime(LocalDateTime.now());
    return new ResponseEntity<>(apiResponse, ex.getErrorCode().getHttpStatus());
  }
}