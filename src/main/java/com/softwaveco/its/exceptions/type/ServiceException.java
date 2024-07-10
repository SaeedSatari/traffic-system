package com.softwaveco.its.exceptions.type;

import org.springframework.http.HttpStatus;

public class ServiceException extends BaseException {
    public ServiceException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
