package com.abdo.learn.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.abdo.learn.model.dto.response.ErrorMessageResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleGlobalException(ResponseStatusException e) {
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatusCode().value())).body(getErrorMessageBody(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleLocalException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorMessageBody(e));
    }

    private ErrorMessageResponse getErrorMessageBody(ResponseStatusException e) {
        return ErrorMessageResponse.builder()
                .message(e.getReason())
                .createAt(LocalDateTime.now())
                .build();
    }

    private ErrorMessageResponse getErrorMessageBody(Exception e) {
        return ErrorMessageResponse.builder()
                .message(e.getMessage())
                .createAt(LocalDateTime.now())
                .build();
    }

}
