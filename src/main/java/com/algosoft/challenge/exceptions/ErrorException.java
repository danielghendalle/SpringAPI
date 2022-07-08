package com.algosoft.challenge.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class ErrorException extends RuntimeException {

    public ErrorException(String exception) {
        super(exception);

    }
}
