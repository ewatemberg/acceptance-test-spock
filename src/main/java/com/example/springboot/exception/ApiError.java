package com.example.springboot.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError<T extends ApiSubError> {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    @JsonProperty("sub_errors")
    private List<T> subErrors;

    private ApiError() {
        subErrors = new ArrayList<>();
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiError<T> addSubError(T error) {
        this.subErrors.add(error);
        return this;
    }
}
