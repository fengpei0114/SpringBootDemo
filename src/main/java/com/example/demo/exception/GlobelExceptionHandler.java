package com.example.demo.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobelExceptionHandler {
    @ExceptionHandler({
            BadRequestException.class,
    })
    @ResponseBody
    public ResponseEntity<Error> handleBadRequestException(Throwable ex) {
        return process(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ConflictException.class,
    })
    @ResponseBody
    public ResponseEntity<Error> handleConflictException(Throwable ex) {
        return process(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
            NoDataFoundException.class,
    })
    @ResponseBody
    public ResponseEntity<Error> handleNoDataFoundException(Throwable ex) {
        return process(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Error> process(Throwable ex, HttpStatus status) {
        return ResponseEntity.status(status).body(Error.of(ex.getMessage()));
    }
}
