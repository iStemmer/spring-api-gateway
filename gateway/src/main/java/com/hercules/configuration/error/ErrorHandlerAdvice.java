package com.hercules.configuration.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

@RestControllerAdvice
@Slf4j
public class ErrorHandlerAdvice {

    @ExceptionHandler({SocketTimeoutException.class})
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ErrorResponse handle(SocketTimeoutException ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorResponse(HttpStatus.GATEWAY_TIMEOUT.toString(), Integer.toString(HttpStatus.GATEWAY_TIMEOUT.value()), ex.getMessage());
    }

    @ExceptionHandler({ConnectException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(ConnectException ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
    }


}



