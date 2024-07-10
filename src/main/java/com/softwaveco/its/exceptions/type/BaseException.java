package com.softwaveco.its.exceptions.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    private final String message;
    @Getter
    private final HttpStatus httpStatus;

    public BaseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
