package com.example.items;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RestResponseExceptionHandler.class);

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleSqlExceptions(
            RuntimeException ex, WebRequest request) {

        logger.error("Catch Exception", ex);
        return handleExceptionInternal(ex, "System not available", new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }


    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<Object> handleDataAccessExceptions(
            RuntimeException ex, WebRequest request) {

        logger.error("Catch Exception", ex);
        return handleExceptionInternal(ex, "Bad request", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
