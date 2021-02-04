package com.example.HealthcareEnrollment.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        logger.error("Exception occurred : ", exception);
        return new ResponseEntity<String>("Error : " + exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        logger.error("Exception occurred : ", exception);
        return new ResponseEntity<String>("Error : " + exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericException(Exception exception) {
        logger.error("Exception occurred : ", exception);
        return new ResponseEntity<String>("Error : " + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
