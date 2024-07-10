package com.softwaveco.its.exceptions.type;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
