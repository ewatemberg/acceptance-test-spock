package com.example.springboot.resource;

import com.example.springboot.config.ApiVersion;
import com.example.springboot.exception.ApiError;
import com.example.springboot.exception.ApiException;
import com.example.springboot.exception.ApiSubError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class BaseResource {

    private static Logger log = LoggerFactory.getLogger(BaseResource.class);

    public static ApiException notFound(String id, String resource) {
        log.error("Resources {}{}/{} not found", ApiVersion.V1, resource, id);
        return new ApiException(new ApiError<>(HttpStatus.NOT_FOUND, String.format("NOT FOUND", resource, id)));
    }

    private ResponseEntity<ApiError<?>> handleError(HttpStatus status, String mensaje) {
        ApiError<?> error = new ApiError<>(status).addSubError(new ApiSubError(mensaje));
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("The value of {} is not valid.", ex.getName(), ex);
        return handleError(HttpStatus.BAD_REQUEST, "The value of '" + ex.getName() + "' is valid");
    }
}
