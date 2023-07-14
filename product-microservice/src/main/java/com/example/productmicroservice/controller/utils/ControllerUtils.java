package com.example.productmicroservice.controller.utils;

import com.example.productmicroservice.dto.message.MessageDto;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ControllerUtils {
    private final MessageSource messageSource;

    public ControllerUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ResponseEntity<MessageDto> createResponseEntityOk(String messageKey) {
        String message = messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto(message));
    }
}