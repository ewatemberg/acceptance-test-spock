package com.example.springboot.exception;

import java.util.function.Supplier;

public class ApiException extends RuntimeException implements Supplier<ApiException> {

    private static final long serialVersionUID = 1L;

    private ApiError<?> error;

    public ApiException() {
    }

    public ApiException(ApiError<?> error) {
        this.error = error;
    }

    public ApiError<?> getError() {
        return error;
    }

    @Override
    public ApiException get() {

        return this;
    }

}
