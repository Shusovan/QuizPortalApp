package com.project.adminservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ApiResponse> handleQuestionNotFoundException(AdminNotFoundException exception)
    {
        ApiResponse apiResponse = new ApiResponse(new Date(), exception.getMessage(), false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    
}
