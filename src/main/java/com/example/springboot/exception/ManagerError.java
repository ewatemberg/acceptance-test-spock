package com.example.springboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ManagerError {

    private static Logger log = LoggerFactory.getLogger(ManagerError.class);

    public static ApiException error(String msg, HttpStatus status) {
        log.error(msg);
        return new ApiException(new ApiError<>(status)
                .addSubError(new ApiSubError(msg)));
    }

}