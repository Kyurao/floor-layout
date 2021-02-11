package com.kyurao.floorlayout.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RoomException.class)
    public ErrorRes handleAnyException(RoomException ex) {
        return new ErrorRes(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRes> handleAnyException(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorRes(ex.getMessage()));
    }
}
